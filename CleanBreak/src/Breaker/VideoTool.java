/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Breaker;

import java.io.*;

/**
 *
 * @author a-scott-1
 */
public class VideoTool {
   BreakView BV = new BreakView();

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      // TODO code application logic here
      BreakView.main();
   }
   
   public void Convert() {
      int OSX = 0, MS = 1;
      String[] HBLoc = {"./Resources/HandBrakeCLI", ".\\Resources\\HandBrakeCLI.exe"};
      String OS = System.getProperty("os.name");
      
      
      if (BreakView.ToConvert == null) {
         BV.SetWarningText("You haven't selected any files to convert.");
         return;
      }
      else if (BreakView.ConvertDest == null) {
         BV.SetWarningText("You haven't selected a destination location for the files.");
         return;
      }
      
      try {
         Runtime RT = Runtime.getRuntime();
         
         String Execute;
         if (OS.toLowerCase().indexOf("mac") >= 0) {
            Execute += HBLoc[OSX];
         }
         else if (OS.toLowerCase().indexOf("win") >= 0) {
            Execute += HBLoc[MS];
         }
         
         Execute += " -i ";
         
         Execute += 
         
         Process PC = RT.exec()
      }
      catch (IOException ioException){
         BV.SetWarningText("It wasn't a clean break. Reason: <add reason>");
      }

   }
   
}
