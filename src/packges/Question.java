/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packges;

/**
 *
 * @author NHT
 */
public class Question {
    private String content;
    private String A;
    private String B;
    private String C;
    private String D;
    private String answer;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getA() {
        return A;
    }

    public void setA(String A) {
        this.A = A;
    }

    public String getB() {
        return B;
    }

    public void setB(String B) {
        this.B = B;
    }

    public String getC() {
        return C;
    }

    public void setC(String C) {
        this.C = C;
    }

    public String getD() {
        return D;
    }

    public void setD(String D) {
        this.D = D;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question()
    {
        
    };

    public Question(String content, String A, String B, String C, String D, String answer) {
        this.content = content;
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.answer = answer;
    }
    
    public void print()
    {
        System.out.println(content);
        System.out.println(A);
        System.out.println(B);
        System.out.println(C);
        System.out.println(D);
        System.out.println(answer);
        
    }
}
