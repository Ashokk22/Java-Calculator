package com.easydynamics.calculator;

public class CalcWrapper {

    private String result;
    private String a;
    private String b;

    public CalcWrapper() {
        result = NumberService.ZERO;
        a = NumberService.ZERO;
        b = NumberService.ZERO;
    }

	/**
	 * @return result
	 */
	public String getResult() {	return result;}
	
	/**
	 * @param set result
	 */
	public void setResult(String result) { this.result = result;}
	
	/**
	 * @return a
	 */
	public String getA() { return a;}
	
	/**
	 * @param set a
	 */
	public void setA(String a) { this.a = a;}
	
	/**
	 * @return b
	 */
	public String getB() { return b;}
	
	/**
	 * @param set b
	 */
	public void setB(String b) { this.b = b;}
    
}