package org.firas.converter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CopyByFieldNameBeanConverterTests {

    public static class FromBase {
        FromBase(int int1, int int2, Integer int3, Integer int4,
                double double1, double double2, Double double3, Double double4) {
            this.int1 = int1;
            this.int2 = int2;
            this.int3 = int3;
            this.int4 = int4;

            this.double1 = double1;
            this.double2 = double2;
            this.double3 = double3;
            this.double4 = double4;
        }

        private int int1;
        private int int2;
        public int getInt2() {
            return int2;
        }

        protected Integer int3;
        protected Integer int4;

        double double1;
        double double2;
        public Double double3;
        public Double double4;
    }

    public static final class From extends FromBase {
        From(int int1, int int2, Integer int3, Integer int4,
                double double1, double double2, Double double3, Double double4,
                boolean boolean1, boolean boolean2, Boolean boolean3, Boolean boolean4,
                String string1, int string2, Integer string3, boolean string4, Boolean string5,
                        double string6, Double string7) {
            super(int1, int2, int3, int4, double1, double2, double3, double4);

            this.boolean1 = boolean1;
            this.boolean2 = boolean2;
            this.boolean3 = boolean3;
            this.boolean4 = boolean4;

            this.string1 = string1;
            this.string2 = Integer.toString(string2);
            this.string3 = null == string3 ? null : string3.toString();
            this.string4 = Boolean.toString(string4);
            this.string5 = null == string5 ? null : string5.toString();
            this.string6 = Double.toString(string6);
            this.string7 = null == string7 ? null : string7.toString();
        }

        public Integer getInt4() {
            return super.int4;
        }

        private boolean boolean1;
        private boolean boolean2;
        public boolean isBoolean1() {
            return boolean1;
        }

        private Boolean boolean3;
        public Boolean getBoolean3() {
            return boolean3;
        }

        protected Boolean boolean4;

        String string1;
        public String string2;
        public String string3;
        public String string4;
        public String string5;
        public String string6;
        public String string7;
    }

    public static class ToBase {
        private int int1;
        private String int2;
        public String getInt2() {
            return int2;
        }
        public void setInt2(String int2) {
            this.int2 = int2;
        }

        protected Integer int3;
        protected String int4;

        private double double1;
        private String double2;
        private Double double3;
        private String double4;
    }

    public static final class To extends ToBase {
        public String getInt4() {
            return super.int4;
        }
        public void setInt4(String int4) {
            super.int4 = int4;
        }

        private boolean boolean1;
        public boolean isBoolean1() {
            return boolean1;
        }
        public void setBoolean1(boolean boolean1) {
            this.boolean1 = boolean1;
        }

        private String boolean2;
        private Boolean boolean3;
        public Boolean getBoolean3() {
            return boolean3;
        }
        public void setBoolean3(Boolean boolean3) {
            this.boolean3 = boolean3;
        }

        protected String boolean4;

        String string1;
        public int string2;
        public Integer string3;
        public boolean string4;
        public Boolean string5;
        public double string6;
        public Double string7;
    }

    @Test
    public void test() throws Exception {
        RandomUtils randomUtils = new RandomUtils();

        for (int i = 0; i < 5; i += 1) {
            int int1 = randomUtils.nextInt();
            int int2 = randomUtils.nextInt();
            Integer int3 = randomUtils.randomInteger();
            Integer int4 = randomUtils.randomInteger();

            double double1 = randomUtils.nextDouble();
            double double2 = randomUtils.nextDouble();
            Double double3 = randomUtils.randomDouble();
            Double double4 = randomUtils.randomDouble();

            boolean boolean1 = randomUtils.nextBoolean();
            boolean boolean2 = randomUtils.nextBoolean();
            Boolean boolean3 = randomUtils.randomBoolean();
            Boolean boolean4 = randomUtils.randomBoolean();

            String string1 = randomUtils.randomString();
            int string2 = randomUtils.nextInt();
            Integer string3 = randomUtils.randomInteger();
            boolean string4 = randomUtils.nextBoolean();
            Boolean string5 = randomUtils.randomBoolean();
            double string6 = randomUtils.nextDouble();
            Double string7 = randomUtils.randomDouble();

            From from = new From(int1, int2, int3, int4, double1, double2, double3, double4,
                    boolean1, boolean2, boolean3, boolean4,
                    string1, string2, string3, string4, string5, string6, string7);

            CopyByFieldNameBeanConverter<From, To> converter1 = new CopyByFieldNameBeanConverter<>(
                    From.class, To.class, false, false);
            To to1 = converter1.convert(from);
            assertNull(to1.getInt2());
            assertNull(to1.getInt4());
            assertEquals(boolean3, to1.getBoolean3());
            assertEquals(string1, to1.string1);
            assertEquals(0, to1.string2);
            assertNull(to1.string3);
            assertEquals(false, to1.string4);
            assertNull(to1.string5);
            assertEquals(0.0, to1.string6, 1e-10);
            assertNull(to1.string7);

            CopyByFieldNameBeanConverter<From, To> converter2 = new CopyByFieldNameBeanConverter<>(
                    From.class, To.class, false);
            To to2 = converter2.convert(from);
            assertEquals(Integer.toString(int2), to2.getInt2());
            assertEquals(null == int4 ? null : int4.toString(), to2.getInt4());
            assertEquals(boolean3, to2.getBoolean3());
            assertEquals(string1, to2.string1);
            assertEquals(string2, to2.string2);
            assertEquals(string3, to2.string3);
            assertEquals(string4, to2.string4);
            assertEquals(string5, to2.string5);
            assertEquals(string6, to2.string6, 1e-10);
            assertEquals(string7, to2.string7);
        }
    }
}