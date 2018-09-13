package exception;

/**
 * Created by Administrator on 2018/8/27.
 */
public class TestException {
    public TestException() {

    }

    boolean testEx(){
        boolean ret = true;

        try {
            ret = testEx1();
        } catch (Exception e) {
            System.out.println("testEx, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx, finally; return value=" + ret);
            return ret;
        }

    }

    private boolean testEx1() {
        boolean ret = true;

        try {
            ret = testEx2();
            if (!ret) {
                return false;
            }
            System.out.println("testEx1, at the end of try");
            return ret;
        } catch (Exception e) {
            System.out.println("testEx1, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx1, finally; return value=" + ret);
            return ret;
        }

    }

    private boolean testEx2() {
        boolean ret = true;

        try {
            int b = 12;
            int c;
            for (int i = 2; i >= -2; i--) {
                c = b / i;
                System.out.println("i=" + i);
            }
        }catch (Exception e){
            System.out.println("testEx2, catch exception");
            ret = false;
            throw e;
        }finally {
            System.out.println("testEx2, finally; return value=" + ret);
            return ret;
        }
    }

    public static void main(String[] args) {
        TestException exception = new TestException();
        try {
            exception.testEx();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
