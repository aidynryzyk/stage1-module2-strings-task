package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] left = signatureString.split("\\(")[0].split(" ");
        String[] right = signatureString.split("\\(")[1].split(", ");
        String accessModifier = null;
        String returnType;
        String name;
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        if (left.length > 2) {
            accessModifier = left[0];
            returnType = left[1];
            name = left[2];
        } else {
            returnType = left[0];
            name = left[1];
        }
        if (right.length > 1) {
            for (int i = 0; i < right.length; i++) {
                String argumentType = right[i].split(" ")[0];
                String argumentName = right[i].split(" ")[1];
                if (i == right.length - 1) {
                    argumentName = argumentName.substring(0, argumentName.length() - 1);
                }
                arguments.add(new MethodSignature.Argument(argumentType, argumentName));
            }
        }

        MethodSignature methodSignature = new MethodSignature(
                name,
                arguments
        );
        methodSignature.setReturnType(returnType);
        if (accessModifier != null) {
            methodSignature.setAccessModifier(accessModifier);
        }

        return methodSignature;
    }
}
