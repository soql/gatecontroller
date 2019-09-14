package pl.net.oth.gatecontroller.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GateEvent {
	@Id
	private String id;
	
	private String direction;
	private Boolean full;
	private Boolean revert;
	private Boolean fast;
	private Double speed;
	private int time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public Boolean getFull() {
		return full;
	}
	public void setFull(Boolean full) {
		this.full = full;
	}
	public Boolean getRevert() {
		return revert;
	}
	public void setRevert(Boolean revert) {
		this.revert = revert;
	}
	public Boolean getFast() {
		return fast;
	}
	public void setFast(Boolean fast) {
		this.fast = fast;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	
}
