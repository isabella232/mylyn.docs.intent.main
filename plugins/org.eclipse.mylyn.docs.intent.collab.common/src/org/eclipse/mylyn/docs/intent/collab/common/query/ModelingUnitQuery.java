package org.eclipse.mylyn.docs.intent.collab.common.query;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import java.util.Collection;

import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ExternalContentReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;

/**
 * An utility class allowing to query the {@link ModelingUnit}.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class ModelingUnitQuery extends AbstractIntentQuery {
	/**
	 * Creates the query.
	 * 
	 * @param repositoryAdapter
	 *            the {@link RepositoryAdapter} to use for querying the repository.
	 */
	public ModelingUnitQuery(RepositoryAdapter repositoryAdapter) {
		super(repositoryAdapter);
	}

	/**
	 * Returns all the {@link org.eclipse.mylyn.docs.intent.core.document.IntentReference}s contained in the
	 * queried {@link org.eclipse.mylyn.docs.intent.core.document.IntentDocument}.
	 * 
	 * @return all the {@link org.eclipse.mylyn.docs.intent.core.document.IntentReference}s contained in the
	 *         queried {@link org.eclipse.mylyn.docs.intent.core.document.IntentDocument}
	 */
	public Collection<ExternalContentReference> getAllExternalContentReferences() {
		IntentDocumentQuery intentDocumentQuery = new IntentDocumentQuery(repositoryAdapter);

		Collection<ExternalContentReference> externalContentReferences = Sets.newLinkedHashSet();
		for (ModelingUnit unit : intentDocumentQuery.getAllModelingUnits()) {
			externalContentReferences.addAll(Sets.newLinkedHashSet(Iterables.filter(unit.getInstructions(),
					ExternalContentReference.class)));
		}
		return externalContentReferences;
	}
}
