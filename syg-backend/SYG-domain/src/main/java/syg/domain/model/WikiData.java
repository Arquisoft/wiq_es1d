package syg.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class WikiData {

	public static final String WIKIDATA_URL = "https://query.wikidata.org/sparql";

	public static final String WIKIDATA_QUERY = "SELECT DISTINCT (strafter(str(?item), \"http://www.wikidata.org/entity/\") AS ?entityId) ?label ?description WHERE {\n"
			+ "  ?item wdt:P31 wd:%s .\n" + "  ?item rdfs:label ?label .\n"
			+ "  ?item schema:description ?description .\n"
			+ "  FILTER(LANG(?label) = \"es\" && LANG(?description) = \"es\")\n" + "	} LIMIT 50";

	private String id;

	private String description;

	private String response;

	public List<WikiData> executeSparqlQuery(String sparqlQuery) {
		List<WikiData> resultList = new ArrayList<>();

		Repository repository = new SPARQLRepository(WIKIDATA_URL);
		RepositoryConnection connection = null;

		try {
			repository.init();
			connection = repository.getConnection();
			TupleQuery query = connection.prepareTupleQuery(sparqlQuery);
			try (TupleQueryResult result = query.evaluate()) {
				while (result.hasNext()) {
					BindingSet bindingSet = result.next();
					String entityId = bindingSet.getValue("entityId").stringValue();
					String description = bindingSet.getValue("description").stringValue();
					String response = bindingSet.getValue("label").stringValue();
					resultList.add(new WikiData(entityId, description, response));
				}
			}
		} catch (RepositoryException e) {
			throw new NullPointerException(); // A cambiar por excepción personalizada
		} finally {
			if (connection != null) {
				connection.close();
			}
			repository.shutDown();
		}

		return resultList;
	}

	public List<Integer> generateUniqueRandomIndex(int size, int excludedIndex, int count) {
		if (count > size - 1) {
			throw new IllegalArgumentException("La cantidad de números solicitada es mayor que el rango disponible.");
		}

		List<Integer> indices = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < count; i++) {
			int randomIndex;
			do {
				randomIndex = random.nextInt(size);
			} while (randomIndex == excludedIndex || indices.contains(randomIndex));

			indices.add(randomIndex);
		}

		return indices;
	}
}
