# code-profanator
Tool that profanates Java code turning it impossible to be read for a human.

Consider the following code for example:

    01  public class HelloWorld {
    02  
    03      public static void main(String[] args) {
    04          HelloWorld program = new HelloWorld();
    05          program.execute();
    06      }
    07  
    08      public void execute() {
    09          String message = "Hello World";
    10          System.out.println(message);
    11      }
    12  }

If we "profanate" the rows 04, 05, 08, 09, 10 and 11 then it will look like that:

    01  public class HelloWorld {
    02  
    03      public static void main(String[] args) {
    04          // \u000a\u0048\u0065\u006c\u006c\u006f\u0057\u006f\u0072\u006c\u0064\u0020\u0070\u0072\u006f\u0067\u0072\u0061\u006d\u0020\u003d\u0020\u006e\u0065\u0077\u0020\u0048\u0065\u006c\u006c\u006f\u0057\u006f\u0072\u006c\u0064\u0028\u0029\u003b
    05          // \u000a\u0070\u0072\u006f\u0067\u0072\u0061\u006d\u002e\u0065\u0078\u0065\u0063\u0075\u0074\u0065\u0028\u0029\u003b
    06      }
    07  
    08      // \u000a\u0070\u0075\u0062\u006c\u0069\u0063\u0020\u0076\u006f\u0069\u0064\u0020\u0065\u0078\u0065\u0063\u0075\u0074\u0065\u0028\u0029\u0020\u007b
    09          // \u000a\u0053\u0074\u0072\u0069\u006e\u0067\u0020\u006d\u0065\u0073\u0073\u0061\u0067\u0065\u0020\u003d\u0020\u0022\u0048\u0065\u006c\u006c\u006f\u0020\u0057\u006f\u0072\u006c\u0064\u0022\u003b
    10          // \u000a\u0053\u0079\u0073\u0074\u0065\u006d\u002e\u006f\u0075\u0074\u002e\u0070\u0072\u0069\u006e\u0074\u006c\u006e\u0028\u006d\u0065\u0073\u0073\u0061\u0067\u0065\u0029\u003b
    11      // \u000a\u007d
    12  }
