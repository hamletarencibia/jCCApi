package jcc.api.objects;

public class Lap {
	private int lapNumber;
	private int hours;
	private byte minutes;
	private byte seconds;
	private byte milliseconds;
	public Lap(int lapNumber, int hours, byte minutes, byte seconds, byte milliseconds) {
		this.lapNumber = lapNumber;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		this.milliseconds = milliseconds;
	}
	public int getLapNumber() {
		return lapNumber;
	}
	public void setLapNumber(int lapNumber) {
		this.lapNumber = lapNumber;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public byte getMinutes() {
		return minutes;
	}
	public void setMinutes(byte minutes) {
		this.minutes = minutes;
	}
	public byte getSeconds() {
		return seconds;
	}
	public void setSeconds(byte seconds) {
		this.seconds = seconds;
	}
	public byte getMilliseconds() {
		return milliseconds;
	}
	public void setMilliseconds(byte milliseconds) {
		this.milliseconds = milliseconds;
	}
	@Override
	public String toString(){
		String lap="#"+this.lapNumber+": "+this.hours+":";
		if(minutes<10)
			lap+="0"+this.minutes+":";
		else
			lap+=this.minutes+":";
		if(seconds<10)
			lap+="0"+this.seconds+":";
		else
			lap+=this.seconds+":";
		if(milliseconds<10)
			lap+="0"+this.milliseconds;
		else
			lap+=this.milliseconds;
		return lap;
	}
}
