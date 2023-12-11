package v1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class V1MiscellaneousJsonDeserializer extends StdDeserializer<V1Miscellaneous> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 105512546338691629L;

	public V1MiscellaneousJsonDeserializer() {
		this(null);
	}

	protected V1MiscellaneousJsonDeserializer(Class<?> vc) {
		super(vc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public V1Miscellaneous deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {

		V1Miscellaneous result = new V1Miscellaneous();

		Map<String, V1Parameter> namedParameters = new HashMap<String, V1Parameter>();

		JsonNode node = null;

		try {

			node = p.getCodec().readTree(p);

			Iterator<String> nodeFieldNames = node.fieldNames();

			while (nodeFieldNames.hasNext()) {

				String nodeFieldName = nodeFieldNames.next();

				V1Parameter parameter = new V1Parameter();

				V1Validation validation = new V1Validation();

				parameter.setValidation(validation);

				namedParameters.put(nodeFieldName, parameter);

				JsonNode valueNode = node.get(nodeFieldName);

				Iterator<JsonNode> elements = valueNode.elements();

				while (elements.hasNext()) {

					JsonNode childNode = elements.next();

					if (childNode instanceof TextNode) {

						parameter.setDescription(childNode.asText());

					} else if (childNode instanceof ArrayNode) {

						List<String> values = new ArrayList<String>();

						int size = childNode.size();

						for (int n = 0; n < size; n++) {

							JsonNode childChildNode = childNode.get(n);

							values.add(childChildNode.asText());
						}

						validation.setValues(values);
					}
				}

			}

		} catch (Exception e) {

			if (null != node) {

				System.err.println(node.toPrettyString());
			}

			e.printStackTrace();
		}

		result.setNamedParameters(namedParameters);

		return result;
	}

}
