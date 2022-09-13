package net.ukr.airsys.efforts.view;

import java.util.Map;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class Printer {

      private PrintWriter outp;
      private PrintWriter outw;
      private Map<String, Double[]> result;

      public Printer(Map<String, Double[]> result) {
          this.result = result;
          outp = new PrintWriter(new OutputStreamWriter(System.out), true);
      }

      public void setWriter(String outFileName) {
    	     try {
    		     outw = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(outFileName)), true);
    	     } catch (FileNotFoundException ex) {
               System.err.println(ex);
    	     }
      }

      public void render() {
    	     printf(outp);

      }

      public void save() {
           printf(outw);
      }

      private void printf(PrintWriter out) {

           out.printf("\n\t%s,\t\t%s,\t%s\n", 
                "Team", "Total effort", "Remaining Effort");

           String format = "\t%s,\t\t%.1f,\t\t%.1f\n";
           result.entrySet().forEach(t ->  out.printf(format, 
                t.getKey(), t.getValue()[0], t.getValue()[1]));
           out.flush();
    }
}