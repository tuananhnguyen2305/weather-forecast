package com.nta.utils;

import com.nta.cms.exceptions.BusinessException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.diff.JsonDiff;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@UtilityClass
@Slf4j
public class JsonUtils {
    public static String getDifference(JsonNode source, JsonNode target) {
        try {
            JsonPatch patch = JsonDiff.asJsonPatch(source, target);

            StringBuilder sb = new StringBuilder();
            String patchString = patch.toString();

            String[] operations = patchString.split(",");
            for (int i = 0; i < operations.length; i++) {
                String op = operations[i];
                if (op.contains("op: replace")) {
                    String path = op.split(";")[1].replace("path: ", "").replaceAll("\"", "").trim();
                    path = path.substring(1);
                    String oldValue = getValue(path, source);
                    String newValue = getValue(path, target);
                    sb.append("Update ").append(path).append(" - From : ").append(oldValue).append(" To ").append(newValue);
                    if (i < operations.length - 1) {
                        sb.append(" ;;");
                    }
                }
            }

            return sb.toString();
        } catch (Exception ex) {
            log.error("Error getDifference: {}", ex.getMessage());
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error getDifference: " + ex.getMessage());
        }
    }

    private static String getValue(String path, JsonNode jsonNode) {
        try {
            String[] items = path.split("/");
            for (String item : items) {
                if (!isNumber(item)) {
                    jsonNode = jsonNode.get(item);
                } else {
                    jsonNode = jsonNode.get(Integer.parseInt(item));
                }
            }
            return jsonNode.toString().replaceAll("\"", "");
        } catch (Exception ex) {
            return "Error getValue";
        }
    }

    private static boolean isNumber(String item) {
        return item.matches("^\\d+$");
    }
}
