package jv.jpatpl.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe utilitaire pour les opérations Jsons
 * 
 */
public final class JsonUtils {

	private JsonUtils() {
		super();
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
