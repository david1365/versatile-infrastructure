package ir.caspco.versatile.context.validation.exception;

import org.hibernate.validator.internal.engine.path.NodeImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;

import javax.validation.ConstraintViolation;
import java.util.*;

/**
 * @author Davood Akbari - 1399
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class ValidationException extends RuntimeException {

    private final Set<ConstraintViolation<Object>> constraintViolations;
    private final Object target;


    public ValidationException(Object target, Set<ConstraintViolation<Object>> constraintViolations) {
        this.target = target;
        this.constraintViolations = constraintViolations;
    }


    public Set<ConstraintViolation<Object>> constraintViolations() {
        return constraintViolations;
    }

    public Object target() {
        return target;
    }

    public Map<Integer, Map<String, List<String>>> nodeViolations() {

        Map<Integer, Map<String, List<String>>> nodeViolations = new HashMap<>();

        constraintViolations.forEach(objectConstraintViolation -> {
            NodeImpl leafNode = ((PathImpl) objectConstraintViolation.getPropertyPath()).getLeafNode();
            Integer index = leafNode.getIndex() == null ? -1 : leafNode.getIndex();
            String name = getName(leafNode, "");

            String messageTemplate = objectConstraintViolation.getMessageTemplate()
                    .replace("{", "")
                    .replace("}", "")
                    .replace("$", "");

            put(nodeViolations, index, name, messageTemplate);

        });

        return nodeViolations;

    }

    private String getName(NodeImpl node, String name) {

        if (node.getParent() == null) {
            return name;
        }

        name = node.getName() == null ? "" :
                (name.isEmpty() ? node.getName() : node.getName() + "." + name);

        name = name.replace("<", "").replace(">", "").replace(" ", ".");

        return getName(node.getParent(), name);

    }

    private void put(Map<Integer, Map<String, List<String>>> nodeViolations,
                     Integer index, String name, String messageTemplate) {

        Map<String, List<String>> nodeViolation = nodeViolations.get(index);
        nodeViolation = nodeViolation == null ? new HashMap<>() : nodeViolation;

        List<String> messages = nodeViolation.get(name);
        messages = messages == null ? new ArrayList<>() : messages;

        messages.add(messageTemplate);
        nodeViolation.put(target.getClass().getName() + "." + name, messages);

        nodeViolations.put(index, nodeViolation);

    }

}



