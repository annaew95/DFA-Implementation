/**************************************************************************************************
 * CSC 4890 FALL 2023                                                                             *
 * PROJECT 1 ANNA WILLIAMS                                                                        *
 * The following code implements a DFA for the following language:                                *
 * Strings of length 3 or 4 using capital letters of the English alphabet,                        *
 * which contain the substring "LSU".                                                             *
 * -------------------------------------------------------------------------                      *
 * Q={INIT, L_ENTERED1, S_ENTERED1, U_ENTERED1, OTHER_CHAR_END,                                   *
 * L_ENTERED2, OTHER_CHAR_START, L_ENTERED3, S_ENTERED2, U_ENTERED2, REJECT}                      *
 * SIGMA={L, S, U, OTHER}                                                                         *
 * S=INIT                                                                                         *
 * F={U_ENTERED1, OTHER_CHAR_END, U_ENTERED2}                                                     *
 * DELTA=                                                                                         *
 *                     L                  S                  U                  OTHER             *
 * -----------------------------------------------------------------------------------------------*
 * INIT                L_ENTERED1        OTHER_CHAR_START    OTHER_CHAR_START   OTHER_CHAR_START  *
 * L_ENTERED1          L_ENTERED2        S_ENTERED1          REJECT             REJECT            *
 * S_ENTERED1          REJECT            REJECT              U_ENTERED1         REJECT            *
 * U_ENTERED1          OTHER_CHAR_END    OTHER_CHAR_END      OTHER_CHAR_END     OTHER_CHAR_END    *
 * OTHER_CHAR_END      REJECT            REJECT              REJECT             REJECT            *
 * L_ENTERED2          REJCT             S_ENTERED2          REJECT             REJECT            *
 * OTHER_CHAR_START    L_ENTERED3        REJECT              REJECT             REJECT            *
 * L_ENTERED3          REJECT            S_ENTERED2          REJECT             REJECT            *
 * S_ENTERED2          REJECT            REJECT              U_ENTERED2         REJECT            *
 * U_ENTERED2          REJECT            REJECT              REJECT             REJECT            *
 * REJECT              REJECT            REJECT              REJECT             REJECT            *
 * ************************************************************************************************/
import java.util.Scanner;
 public class csc4890_prog1
{
    public static void main(String[] args)
    {
        String word;
        int length;
        char token;
        Scanner scan=new Scanner(System.in);
        System.out.println("WELCOME. PLEASE ENTER A STRING TO BE PROCESSED. TO EXIT THE PROGRAM, ENTER THE $ SYMBOL\n-------------------------------------------------------------------");
        while (true)
        {
            word=scan.next();
            if (word.equals("$")) break;
            length=word.length();
            State current_state=new INIT();
            State next_state=current_state;
            for (int i=0;i<length;i++)
            {
            token=word.charAt(i);
            if ((token<65) || (token>90))
            {
                System.out.println("ERROR: STRING CONTAINS A CHARACTER NOT CONTAINED IN THE DFA'S ALPHABET. PLEASE ONLY USE CAPITAL LETTERS.");
                break;
            }
            next_state=current_state.delta(token);
            System.out.println("STEP "+i+":\nCURRENTLY IN THE <"+current_state.get_name()+"> STATE...");
            System.out.println("TOKEN PROCESSED='"+token+"'...");
            System.out.println("NOW ENTERING THE <"+next_state.get_name()+"> STATE");
            System.out.println("-------------------------------------------------------------------");
            current_state=next_state;  
            }
            if (current_state.is_final())
                System.out.println("STRING "+word+" ACCEPTED BY THE DFA\n-------------------------------------------------------------------");
            else
                System.out.println("STRING "+word+" REJECTED BY THE DFA\n-------------------------------------------------------------------");
            System.out.println("PLEASE ENTER ANOTHER STRING TO BE PROCESSED:");    
        }
        System.out.println("HAVE A NICE DAY");        
    }
}

abstract class State
{
    public String get_name()
    {
        return "STATE";
    }
    public boolean is_final()
    {
        return false;
    } 
    public State delta(char token)
    {
        return null;
    }
}
class INIT extends State
{
    public String get_name()
    {
        return "INIT";
    }
    public boolean is_final()
    {
        return false;
    } 
    public State delta(char token)
    {
        if (token=='L') return new L_ENTERED1();
        else return new OTHER_CHAR_START();
    }
}
class L_ENTERED1 extends State
{
    public String get_name()
    {
        return "L_ENTERED1";
    }
    public boolean is_final()
    {
        return false;
    } 
    public State delta(char token)
    {
        if (token=='L') return new L_ENTERED2();
        else if (token=='S') return new S_ENTERED1();
        else return new REJECT();
    }
}
class S_ENTERED1 extends State
{
    public String get_name()
    {
        return "S_ENTERED1";
    }
    public boolean is_final()
    {
        return false;
    }
    public State delta(char token)
    {
        if (token=='U') return new U_ENTERED1();
        else return new REJECT();
    }
}
class U_ENTERED1 extends State
{
    public String get_name()
    {
        return "U_ENTERED1";
    }
    public boolean is_final()
    {
        return true;
    }
    public State delta(char token)
    {
        return new OTHER_CHAR_END();
    }
}
class OTHER_CHAR_END extends State
{
    public String get_name()
    {
        return "OTHER_CHAR_END";
    }
    public boolean is_final()
    {
        return true;
    }
    public State delta(char token)
    {
        return new REJECT();
    }
}
class L_ENTERED2 extends State
{
    public String get_name()
    {
        return "L_ENTERED2";
    }
    public boolean is_final()
    {
        return false;
    }
    public State delta(char token)
    {
        if (token=='S') return new S_ENTERED2();
        else return new REJECT();
    }
}
class OTHER_CHAR_START extends State
{
    public String get_name()
    {
        return "OTHER_CHAR_START";
    }
    public boolean is_final()
    {
        return false;
    }
    public State delta(char token)
    {
        if (token=='L') return new L_ENTERED3();
        else return new REJECT();
    }
}
class L_ENTERED3 extends State
{
        public String get_name()
    {
        return "L_ENTERED3";
    }
    public boolean is_final()
    {
        return false;
    }
    public State delta(char token)
    {
        if (token=='S') return new S_ENTERED2();
        else return new REJECT();
    }
}
class S_ENTERED2 extends State
{
    public String get_name()
    {
        return "S_ENTERED2";
    }
    public boolean is_final()
    {
        return false;
    }
    public State delta(char token)
    {
        if (token=='U') return new U_ENTERED2();
        else return new REJECT();
    }
}
class U_ENTERED2 extends State
{
    public String get_name()
    {
        return "U_ENTERED2";
    }
    public boolean is_final()
    {
        return true;
    }
    public State delta(char token)
    {
        return new REJECT();
    }
}
class REJECT extends State
{
    public String get_name()
    {
        return "REJECT";
    }
    public boolean is_final()
    {
        return false;
    }
    public State delta(char token)
    {
        return new REJECT();
    }
}