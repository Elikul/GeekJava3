public class TestTAsk {

        @BeforeSuite
        public void taskBefore() {
            System.out.println(getClass().getSimpleName() + " before");
        }

        @Test
        public void task1() {
            System.out.println(getClass().getSimpleName() + " task1");
        }

        @Test(priority = 2)
        public void task2() {
            System.out.println(getClass().getSimpleName() + " task2");
        }

        @Test(priority = 7)
        public void task3() {
            System.out.println(getClass().getSimpleName() + " task3");
        }

        @Test(priority = 5)
        public void task4() {
            System.out.println(getClass().getSimpleName() + " task3");
        }

        @AfterSuite
        public void taskAfter() {
            System.out.println(getClass().getSimpleName() + " after");
        }

//        @BeforeSuite //
//        public void taskBefore2() {}
}
