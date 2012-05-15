/*******************************************************************************
 * Copyright (c) 2010, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

import java.util.Collection;
import java.util.Set;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditorDocument;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;

/**
 * Ensures that the internal structure of the Intent Repository is correctly maintained.
 * <p>
 * Relevant issues :
 * <ul>
 * <li>"xmi files are not deleted" https://bugs.eclipse.org/bugs/show_bug.cgi?id=365088</li>
 * </ul>
 * </p>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentRepositoryStructurerTest extends AbstractIntentUITest {

	private static final String NEW_CHAPTER = "\tChapter {\n\t\tSection {\n\t\t}\n\t}\n}";

	/**
	 * Ensures that the internal structure of the Intent Repository is correctly maintained expected (on file
	 * per Chapter/Section/Modeling Unit, see
	 * {@link org.eclipse.mylyn.docs.intent.client.ui.ide.repository.IntentWorkspaceRepositoryStructurer}).
	 */
	public void testIntentRepositoryHasExpectedStructureWhenAddingAndRemovingChapters() {
		// Step 1: we initialize an intent project
		setUpIntentProject("intentProject", "data/unit/documents/editorupdates/compareTest-01.intent");
		IntentEditor editor = openIntentEditor();

		// Step 2: we check the initial structure of the project
		checkRepositoryStructure(Lists.newArrayList("1", "2", "3"),
				Lists.newArrayList("1.1", "2.1", "2.2", "3.1", "3.1.1", "3.1.2"),
				Lists.newArrayList("1.1.1", "3.1.1"));

		// Step 3: adding a new chapter a the begging
		// => all previous content should be stored in new resources
		IntentEditorDocument document = (IntentEditorDocument)editor.getDocumentProvider().getDocument(
				editor.getEditorInput());
		document.set("Document {\n\tChapter {\n\tNew Chapter\n\tNew Chapter\n\tSection {\n\t}\n\t}\n"
				+ document.get().replace("Document {", ""));

		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();
		checkRepositoryStructure(Lists.newArrayList("1", "2", "3", "4"),
				Lists.newArrayList("1.1", "2.1", "3.1", "3.2", "4.1", "4.1.1", "4.1.2"),
				Lists.newArrayList("2.1.1", "4.1.1"));

		// Step 4: adding a new chapter at the end
		String documentWithChapterAtTheEnd = document.get().substring(0, document.get().lastIndexOf("}"))
				+ NEW_CHAPTER;

		document.set(documentWithChapterAtTheEnd);
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();
		checkRepositoryStructure(Lists.newArrayList("1", "2", "3", "4", "5"),
				Lists.newArrayList("1.1", "2.1", "3.1", "3.2", "4.1", "4.1.1", "4.1.2", "5.1"),
				Lists.newArrayList("2.1.1", "4.1.1"));

		// Step 5 : adding a new chapter in the middle
		String chapterToAdd = "";
		int index = document.get().indexOf("The 2.2 Section");
		chapterToAdd = document.get().substring(index);
		chapterToAdd = chapterToAdd.substring(chapterToAdd.indexOf("Chapter"),
				chapterToAdd.lastIndexOf("Chapter"));
		document.set(document.get().replace(chapterToAdd, chapterToAdd + NEW_CHAPTER));
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();
		checkRepositoryStructure(Lists.newArrayList("1", "2", "3", "4", "5"),
				Lists.newArrayList("1.1", "2.1", "3.1", "3.2", "4.1", "4.1.1", "4.1.2", "5.1"),
				Lists.newArrayList("2.1.1", "4.1.1"));

		// Step 6 : deleting a chapter in the middle
		String chapterToDelete = "";
		index = document.get().indexOf("The 2.2 Section");
		chapterToDelete = document.get().substring(index);
		chapterToDelete = chapterToDelete.substring(chapterToDelete.indexOf("Chapter"),
				chapterToDelete.lastIndexOf("Chapter"));
		document.set(document.get().replace(chapterToDelete, ""));
		editor.doSave(new NullProgressMonitor());
		waitForAllOperationsInUIThread();
		checkRepositoryStructure(Lists.newArrayList("1", "2", "3", "4"),
				Lists.newArrayList("1.1", "2.1", "3.1", "3.2", "4.1"), Lists.newArrayList("2.1.1"));
	}

	/**
	 * Checks that the structure of the repository is conform to the given specifications.
	 * 
	 * @param expectedChapterNames
	 *            the list of chapters resources that should be in the repository
	 * @param expectedSectionNames
	 *            the list of section resources that should be in the repository
	 * @param expectedMUNames
	 *            the list of Modeling unit resources that should be in the repository
	 */
	protected void checkRepositoryStructure(Collection<String> expectedChapterNames,
			Collection<String> expectedSectionNames, Collection<String> expectedMUNames) {
		IFolder documentFolder = intentProject.getFolder(".repository/" + IntentLocations.INTENT_FOLDER);
		try {
			documentFolder.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());

			IFolder chapterFolder = documentFolder.getFolder("IntentChapter");
			IFolder sectionFolder = documentFolder.getFolder("IntentSection");
			IFolder MUFolder = documentFolder.getFolder("ModelingUnit");

			checkFolderStructure(chapterFolder, Sets.newLinkedHashSet(expectedChapterNames));
			checkFolderStructure(sectionFolder, Sets.newLinkedHashSet(expectedSectionNames));
			checkFolderStructure(MUFolder, Sets.newLinkedHashSet(expectedMUNames));
		} catch (CoreException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Ensures that the given folder contains exactly the given expected resources.
	 * 
	 * @param folder
	 *            the folder to check
	 * @param expectedResourcesName
	 *            the expected resources
	 */
	protected void checkFolderStructure(IFolder folder, Set<String> expectedResourcesName) {
		Set<String> folderContent = Sets.newLinkedHashSet();
		try {
			// Collecting the resources contained by the given folder
			for (IResource resource : folder.members()) {
				folderContent.add(resource.getName().replace("." + resource.getFileExtension(), ""));
			}

			// Checking that the folder does not contain more resource that the expected ones
			SetView<String> folderDifferences = Sets.difference(folderContent, expectedResourcesName);
			assertTrue("The " + folder.getName() + " folder contains too many " + folder.getName() + "(s) ("
					+ folderDifferences.size() + "): " + folderDifferences.toString(),
					folderDifferences.isEmpty());

			// Checking that the folder does contain all expected ones
			folderDifferences = Sets.difference(expectedResourcesName, folderContent);
			assertTrue("The " + folder.getName() + " folder should contain the following " + folder.getName()
					+ "(s): " + folderDifferences.toString(), folderDifferences.isEmpty());
		} catch (CoreException e) {
			fail(e.getMessage());
		}
	}

}
