package com.purple.json;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericData.Record;
import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.purple.json.converter.AvroConverter;
import com.purple.json.converter.JsonAvroConverter;
import com.purple.json.converter.JsonGenericRecordReader;

public class App {

	public static String genateSchema(String jsonPath) {
		String request;
		try {
			request = FileUtils.readFileToString(new File(jsonPath), StandardCharsets.UTF_8);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

			String shema = new AvroConverter(mapper).convert(request);
			return shema;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Record parse(String jsonPath) {

		try {
			String request = FileUtils.readFileToString(new File(jsonPath), StandardCharsets.UTF_8);

			String shema = genateSchema(jsonPath);
			System.out.println("App.parse() shema " + shema);
			JsonAvroConverter converter = new JsonAvroConverter();
			GenericData.Record record = converter.convertToGenericDataRecord(request.getBytes(),
					new Schema.Parser().parse(shema));

			System.out.println("App.parse() record " + record);
			final Schema schema = record.getSchema();

			System.out.println("Schema para " + schema.getDoc());
			System.out.println(schema.toString(true));
			System.out.println();
			schema.getFields().forEach(f -> {
				System.out.println(">>> " + f.doc() + ": " + record.get(f.name()));
				
			});

			return record;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) throws IOException {

		String jsonPath = "E:\\Mahesh\\Project\\Purplecube\\JSON\\nested_json1.json";
		parse(jsonPath);
	}
}
