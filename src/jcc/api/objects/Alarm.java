package jcc.api.objects;

public class Alarm {
	private byte hour;
	private byte minutes;
	private byte[] days;
	public Alarm(int hour, int minutes, byte[] days) {
		this.hour = (byte)hour;
		this.minutes = (byte) minutes;
		this.days = days;
	}
	public byte getHour() {
		return hour;
	}
	public void setHour(byte hour) {
		this.hour = hour;
	}
	public byte getMinutes() {
		return minutes;
	}
	public void setMinutes(byte minutes) {
		this.minutes = minutes;
	}
	public byte[] getDays() {
		return days;
	}
	public void setDays(byte[] days) {
		this.days = days;
	}
}