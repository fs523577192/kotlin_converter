package org.firas.converter;

import kotlin.random.Random;

/**
 * @author Wu Yuping
 */
class RandomUtils {

    private Random random = Random.Default;

    boolean nextBoolean() {
        return random.nextBoolean();
    }

    int nextInt() {
        return random.nextInt();
    }

    double nextDouble() {
        return random.nextDouble();
    }

    Integer randomInteger() {
        return random.nextBoolean() ? null : random.nextInt();
    }

    Double randomDouble() {
        return random.nextBoolean() ? null : random.nextDouble();
    }

    Boolean randomBoolean() {
        switch (random.nextInt(0, 3)) {
            case 1:
                return true;
            case 2:
                return false;
            default:
                return null;
        }
    }

    String randomString() {
        int length = random.nextInt(-1, 100);
        if (length < 0) {
            System.out.println("Generated String: null");
            return null;
        }

        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < length; i += 1) {
            int c = random.nextInt((int)' ', 127 + 3);
            switch (c) {
                case 127:
                    buffer.append('\t');
                    break;
                case 128:
                    buffer.append('\r');
                    break;
                case 129:
                    buffer.append('\n');
                    break;
                default:
                    buffer.append( (char)c );
            }
        }
        String result = buffer.toString();
        System.out.println("Generated String: " + result);
        return result;
    }
}
