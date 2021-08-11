package jcc.api.javafx.animation;

import javafx.animation.Transition;
import javafx.scene.control.Labeled;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class TextFillTransition extends Transition {
	private Color from;
	private Color to;
	private Labeled node;
	
	public TextFillTransition(Duration duration) {
		setCycleDuration(duration);
	}
	public TextFillTransition(Labeled node) {
		this.node = node;
	}
	public TextFillTransition(Duration duration, Labeled node) {
		this.node = node;
		setCycleDuration(duration);
	}
		
	@Override
	protected void interpolate(double arg0) {
		double red = to.getRed() - from.getRed();
		double currentRed = arg0 * red;
		double green = to.getGreen() - from.getGreen();
		double currentGreen = arg0 * green;
		double blue = to.getBlue() - from.getBlue();
		double currentBlue = arg0 * blue;
		double opacity = to.getOpacity() - from.getOpacity();
		double currentOpacity = arg0 * opacity;
		node.setTextFill(new Color(from.getRed()+currentRed,from.getGreen()+currentGreen,from.getBlue()+currentBlue,from.getOpacity()+currentOpacity));
	}
	public Color getFrom() {
		return from;
	}
	public void setFrom(Color from) {
		this.from = from;
	}
	public Color getTo() {
		return to;
	}
	public void setTo(Color to) {
		this.to = to;
	}
	public Labeled getNode() {
		return node;
	}
	public void setNode(Labeled node) {
		this.node = node;
	}
	public Duration getDuration() {
		return getCycleDuration();
	}
	public void setDuration(Duration duration) {
		setCycleDuration(duration);
	}
}
