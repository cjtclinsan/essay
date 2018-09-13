package listener;

/**
 * Created by Administrator on 2018/8/10.
 */
public class TestGame {
    public static void main(String[] args) {
        int tianM = 0, tianN = 0;
        int longM = 0, longN = 0;

        for( int i = 1; i < 15; i++ ){
            tianM = i;
            for( int j = 1; j < 15; j++ ){
                tianN = j;
                for( int k = 1; k < 15; k++ ){
                    longM = k;
                    for( int l = 1; l < 15; l++ ){
                        longN = l;
                        if( tianM+tianN+longM+longN == 15 ){
                            if( (tianM+tianN) > (longM+longN) ){
                                if( longM > tianM ){
                                    if( tianM > tianN ){
                                        if( longN >= 1 ){
                                            System.out.println(tianM+"  "+tianN+"  "+longM+" "+longN);
                                        }
                                    }
                                }
                            }
                        }
                    }

                }

            }
        }

    }
}