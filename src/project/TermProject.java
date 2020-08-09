package project;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Malan
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
public class TermProject extends JFrame implements ActionListener{
    
    private JDialog results;
    private JWindow vwindow;
    private JLabel prompt, etitle, exp, ctitle, constants, vtitle, variables, ptitle, postfix, stitle, solution, vprompt, vname;
    private JTextField input, display;
    private JButton enter, enter2, one, two, three, four, five, six, seven, eight, nine, zero, negative, clear;
    
    private String e = "", cstring, vstring, s;
    private ArrayList<String> earrlist, carrlist, varrlist;
    String[] earray, carray, varray, parray, vvalues, operatorstack;
    private int[] valuestack;
    private int count, topofstack;
    
    public static void main(String[] args) {             
        TermProject f = new TermProject();    
    }
    
    private TermProject(){
        setTitle("Term Project");
        setSize(450, 200);
        setLayout(null);
       
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        prompt = new JLabel("Enter Expression");     
        prompt.setBounds(10, 0, 100, 40);
        add(prompt);
        
        input = new JTextField();    
        input.setBounds(10, 40, 400, 40);
        add(input);
        
        enter = new JButton("Enter");
        enter.setBounds(285, 100, 125, 40);
        add(enter);
        enter.addActionListener(this); 
        
        setVisible(true);

        etitle= new JLabel("Expression");       
        exp = new JLabel("");
        ctitle = new JLabel("Constants");   
        constants = new JLabel("");       
        vtitle = new JLabel("Variables");      
        variables = new JLabel("");     
        ptitle = new JLabel("Postfix");     
        postfix = new JLabel("");    
        stitle = new JLabel("Solution");
        solution = new JLabel("");
        
    }
   
    private void createVariableWindow(){
        vvalues = new String[varray.length];
        count = 0;
        vwindow = new JWindow();
        vwindow.setSize(250, 430);
        vwindow.setLayout(null);
        vwindow.setVisible(true);
        
        vprompt = new JLabel("Enter value for each variable:");
        vname = new JLabel(varray[0]);
        display = new JTextField("");
        display.setEditable(false);
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");
        four = new JButton("4");
        five = new JButton("5");
        six = new JButton("6");
        seven = new JButton("7");
        eight = new JButton("8");
        nine = new JButton("9");
        zero = new JButton("0");
        enter2 = new JButton("Enter");
        negative = new JButton("-");
        clear = new JButton("Ce");
        
        vprompt.setBounds(25, 10, 200, 40);
        vname.setBounds(25, 30, 100, 40);
        display.setBounds(25, 70, 200, 40);
        one.setBounds(25, 120, 50, 40);
        two.setBounds(100, 120, 50, 40);    
        three.setBounds(175, 120, 50, 40);
        four.setBounds(25, 180, 50, 40);
        five.setBounds(100, 180, 50, 40);
        six.setBounds(175, 180, 50, 40);
        seven.setBounds(25, 240, 50, 40);
        eight.setBounds(100, 240, 50, 40);
        nine.setBounds(175, 240, 50, 40);
        negative.setBounds(25, 300, 50, 40);
        zero.setBounds(100, 300, 50, 40);    
        clear.setBounds(175, 300, 50, 40);
        enter2.setBounds(100, 360, 125, 40);
        
        vwindow.add(vprompt);
        vwindow.add(vname);
        vwindow.add(display);
        vwindow.add(one);
        vwindow.add(two);
        vwindow.add(three);        
        vwindow.add(enter2);
        vwindow.add(four);
        vwindow.add(five);
        vwindow.add(six);
        vwindow.add(seven);
        vwindow.add(eight);
        vwindow.add(nine);
        vwindow.add(zero); 
        vwindow.add(negative);
        vwindow.add(clear);
        
        one.addActionListener(this);
        two.addActionListener(this);
        three.addActionListener(this);
        four.addActionListener(this);
        five.addActionListener(this);
        six.addActionListener(this);
        seven.addActionListener(this);
        eight.addActionListener(this);
        nine.addActionListener(this);
        zero.addActionListener(this);
        enter2.addActionListener(this);
        negative.addActionListener(this);
        clear.addActionListener(this);
    }
              
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==enter){
            createExpressionArray(input.getText());
            exp.setText(input.getText());
            input.setText(""); 
            
            createConstantArray();
            createVariableArray();  
            constants.setText(cstring);

            if (!"".equals(vstring)){
                createVariableWindow();
            }
            else {
                variables.setText("");
                infixToPostfix();
                variableValues();
                solve();
                displayResults();
            }
        }
        
        if (e.getSource()==negative){
            display.setText(display.getText().concat("-"));
        }
        if (e.getSource()==one){
            display.setText(display.getText().concat("1"));
        }
        if (e.getSource()==two){
            display.setText(display.getText().concat("2"));
        }
        if (e.getSource()==three){
            display.setText(display.getText().concat("3"));
        }
        if (e.getSource()==four){
            display.setText(display.getText().concat("4"));
        }
        if (e.getSource()==five){
            display.setText(display.getText().concat("5"));
        }
        if (e.getSource()==six){
            display.setText(display.getText().concat("6"));
        }
        if (e.getSource()==seven){
            display.setText(display.getText().concat("7"));
        }
        if (e.getSource()==eight){
            display.setText(display.getText().concat("8"));
        }
        if (e.getSource()==nine){
            display.setText(display.getText().concat("9"));
        }
        if (e.getSource()==zero){
            display.setText(display.getText().concat("0"));
        }
        if (e.getSource()==clear){
            display.setText("");
        }
        if (e.getSource()==enter2){
            
            vvalues[count] = display.getText();
            count++;
            if (count == varray.length){
                vwindow.dispose();
                vstring = varray[0]+"="+vvalues[0];
                for (int i = 1; i < varray.length; i++){ 
                    vstring+=", "+varray[i]+"="+vvalues[i]; 
                } 
                variables.setText(vstring);
                infixToPostfix();
                variableValues();
                solve();
                displayResults();
            }
            else{
                vname.setText(varray[count]);
                display.setText("");
            }
        }
    }
    
    private void createExpressionArray(String s){
        e = s;
        earrlist = new ArrayList<>();            

        int intfirst = 0;
        int intsize=0;
        for (int j = 0; j < e.length(); j++) {
            if (e.charAt(j) == '+' || e.charAt(j) == '*' || e.charAt(j) == '/' || e.charAt(j) == '%'){ 
                if (e.charAt(j-1) == ')'){
                    earrlist.add(e.substring(j, j+1));
                    intfirst = j+1;
                    intsize = 0;   
                }
                else{
                earrlist.add(e.substring(intfirst, intfirst+intsize));                    
                intfirst = j+1;
                intsize = 0;     
                earrlist.add(e.substring(j, j+1));   
                }   
            }
            else if (e.charAt(j) == '-'){
                if (j==0)
                    intsize++;
                else if (e.charAt(j-1) == '+' || e.charAt(j-1) == '-' || e.charAt(j-1) == '*' || e.charAt(j-1) == '/' || e.charAt(j-1) == '%' || e.charAt(j-1) == '(')
                    intsize++;
                else if (e.charAt(j-1) == ')'){
                    earrlist.add(e.substring(j, j+1)); 
                    intfirst = j+1;
                    intsize = 0;   
                }
                else{
                    earrlist.add(e.substring(intfirst, intfirst+intsize));
                    intfirst = j+1;
                    intsize = 0; 
                    earrlist.add(e.substring(j, j+1));   
                }
            }
            
            else if (e.charAt(j) == ')'){
                if (j != e.length()-1){
                    if (e.charAt(j-1) != ')'){
                        earrlist.add(e.substring(intfirst, intfirst+intsize));             
                        intfirst = j+1;
                        intsize = 0;
                    }
                        earrlist.add(e.substring(j, j+1));
                }       
                else{
                    if (e.charAt(j-1) != ')'){
                    earrlist.add(e.substring(intfirst, j));
                    }
                    intfirst = j;                   
                }
            }
            
            else if (e.charAt(j) == '('){
                earrlist.add(e.substring(j, j+1));
                intfirst = j+1;
            }
            
            else
                intsize++;    
            
            if (j == e.length()-1)
                earrlist.add(e.substring(intfirst, e.length()));        
        }
        earray = earrlist.toArray(new String[earrlist.size()]);     
    }
    
    private void createConstantArray(){
        carrlist = new ArrayList<>();   
        for (String e : earray) {
            if ((e.charAt(0) >= '0' && e.charAt(0) <= '9') || (e.charAt(0) == '-' && e.length() > 1))
                carrlist.add(e);   
        }   
        if (!carrlist.isEmpty()){
            carray = carrlist.toArray(new String[carrlist.size()]);
            cstring = carray[0];
            for (int i = 1; i < carray.length; i++) 
                cstring+=", "+carray[i];
        }
        else
            cstring = "";            
    }
    
    private void createVariableArray(){
        varrlist = new ArrayList<>();
        boolean duplicate = false;
        for (int i = 0; i < earray.length; i++) {
            if ((earray[i].charAt(0) >= 'a' && earray[i].charAt(0) <= 'z') || (earray[i].charAt(0) >= 'A' && earray[i].charAt(0) <= 'Z')) {
                for (int j = i-1; j >= 0; j--) {
                    if (earray[i].equals(earray[j])){
                        duplicate = true;
                        break;
                    }   
                }
                if (duplicate == false){
                varrlist.add(earray[i]);
                }
                duplicate = false;
            }    
        }
        
        if (!varrlist.isEmpty())
            varray = varrlist.toArray(new String[varrlist.size()]);
        else
            vstring = "";
    }
    
    
    private void infixToPostfix(){
        int i = earray.length;
        for (String e: earray) 
            if (e.equals("(") || e.equals(")"))
                i--;
        parray = new String[i];
        operatorstack = new String[i];
        count = 0;
        topofstack = -1;
        for (String e : earray) {
            
            switch (e) {
                case "/":
                case "*":
                case "%":
                    if (topofstack == -1) {
                        topofstack++;
                        operatorstack[topofstack] = e;
                    }
                    else {
                        while( operatorstack[topofstack].equals("*") || operatorstack[topofstack].equals("/") || operatorstack[topofstack].equals("%") ){
                            parray[count] = operatorstack[topofstack];
                            operatorstack[topofstack] = "";
                            count++;
                            topofstack--;
                            if (topofstack==-1 || operatorstack[topofstack].equals("("))
                                break;
                        }
                        topofstack++;
                        operatorstack[topofstack] = e;
                    }   break;
                case "+":
                case "-":
                    while(topofstack>-1){
                        if (operatorstack[topofstack].equals("("))
                            break;
                        parray[count] = operatorstack[topofstack];
                        operatorstack[topofstack] = "";
                        count++;
                        topofstack--;
                    }   topofstack++;
                    operatorstack[topofstack] = e;
                    break;
                case "(":
                    topofstack++;
                    operatorstack[topofstack] = e;
                    break;
                case ")":
                    while( topofstack >-1 ){
                        if (operatorstack[topofstack].equals("(")){
                            operatorstack[topofstack] = "";
                            topofstack--;
                            break;
                        }
                        else{
                            parray[count] = operatorstack[topofstack];
                            operatorstack[topofstack] = "";
                            count++;
                            topofstack--;
                        }
                    }   break;
                default:
                    parray[count] = e;
                    count++;
                    break;
            }
            
        } 
        
        while (topofstack > -1){
            parray[count] = operatorstack[topofstack];
            operatorstack[topofstack] = "";
            count++;
            topofstack--;  
        }
        
        String pstring = "";
        for (String parray1: parray){
            pstring+=parray1+" ";
        }
        postfix.setText(pstring);
    }
    
    private void variableValues(){
        count = 0;
        for (int i = 0; i < parray.length; i++) {
            if ((parray[i].charAt(0) >= 'a' && parray[i].charAt(0) <= 'z') || (parray[i].charAt(0) >= 'A' && parray[i].charAt(0) <= 'Z')) { 
                
                for (int j = i+1; j < parray.length; j++) {
                    if (parray[j].equals(parray[i])){
                        parray[j] = vvalues[count];
                    }                 
                } 
                parray[i] = vvalues[count];
                count++;    
                
            }
        }
    }  
    
    private void solve(){
        valuestack = new int[parray.length];
        topofstack = -1;
        
        for(String e: parray){
            if (e.equals("+")){
                valuestack[topofstack-1] += valuestack[topofstack];
                topofstack--;
            }
            else if (e.equals("-")){
                valuestack[topofstack-1] -= valuestack[topofstack];
                topofstack--;
            }
            else if (e.equals("*")){
                valuestack[topofstack-1] *= valuestack[topofstack];
                topofstack--;
            }
            else if (e.equals("/")){
                valuestack[topofstack-1] /= valuestack[topofstack];
                topofstack--;
            }
            else if (e.equals("%")){
                valuestack[topofstack-1] %= valuestack[topofstack];
                topofstack--;
            }
            else{
                topofstack++;
                if (e.charAt(0) == '-')
                    valuestack[topofstack] = 0-Integer.parseInt(e.substring(1, e.length()));     
                else
                    valuestack[topofstack] = Integer.parseInt(e);
            }
        }
        s = Integer.toString(valuestack[0]);
        solution.setText(s);
        
    }
    
    private void displayResults(){  
        results = new JDialog();
        results.setSize(300, 370);
        results.setLayout(null);
        results.setVisible(true);
        
        etitle.setBounds(10, 0, 100, 60);
        exp.setBounds(10, 30, 400, 40);
        ctitle.setBounds(10, 60, 100, 60);
        constants.setBounds(10, 90, 400, 40);
        vtitle.setBounds(10, 120, 100, 60);
        variables.setBounds(10, 150, 400, 40);
        ptitle.setBounds(10, 190, 100, 40);
        postfix.setBounds(10, 210, 400, 40);
        stitle.setBounds(10, 250, 100, 40);
        solution.setBounds(10, 270, 100, 40);
         
        results.add(etitle);
        results.add(exp);
        results.add(ctitle);
        results.add(constants);
        results.add(vtitle);
        results.add(variables);
        results.add(ptitle);
        results.add(postfix);
        results.add(stitle);
        results.add(solution);
    }
    
}
