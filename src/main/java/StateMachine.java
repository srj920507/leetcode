import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateMachine {

    public static void main(String[] args) {

        int[] nums = {1, 3, 2, 3, 4, 5,6};
        StateMachine arrayTest = new StateMachine();

//        int[] res = arrayTest.calculateProduct(nums);
//
//        for (int i = 0; i < res.length; i++) {
//            System.out.print(res[i] + " ");
//        }



        System.out.println( arrayTest.stringtoNum(" ￥￥1343$%^%&"));


    }





    public long stringtoNum(String str){
        AutoMachine autoMachine = new AutoMachine();
        return autoMachine.runMachine(str);
    }

    class AutoMachine{

        private  Map<String, List<String>> states = new HashMap<String, List<String>>(){
            {
                put("start", new ArrayList<String>(){{
                    add("start");
                    add("signal");
                    add("num");
                    add("end");
                }
                });
                put("signal", new ArrayList<String>(){{
                    add("end");
                    add("end");
                    add("num");
                    add("end");

                }
                });
                put("num", new ArrayList<String>(){{
                    add("end");
                    add("end");
                    add("num");
                    add("end");
                }
                });
                put("end", new ArrayList<String>(){{
                    add("end");
                    add("end");
                    add("end");
                    add("end");
                }
                });
            }
        };


       private String state = "start";



       private void transferToNextState(Character c){

          List<String> curStateList =  states.get(state);
          int type;
          if(c==' '){
            type=0;
          }else if(c == '-' || c == '+'){
              type =1;
          }else if(c >= '0' && c<='9'){
              type = 2;
          }else{
              type = 3;
          }

          state = curStateList.get(type);

       }

       public long runMachine(String str){
           long ans = 0;
           long signal = 1;
           for(int i=0;i<str.length();i++){
               transferToNextState(str.charAt(i));
               if(state.equals("num")){
                  ans= ans*10+ (str.charAt(i)-'0');
               }
               if(state.equals("signal")){
                   if(str.charAt(i) == '-'){
                       signal = -1;
                   }
               }
               if(state.equals("end")){
                   return signal*ans;
               }
           }

           return signal*ans;
       }


    }


}
