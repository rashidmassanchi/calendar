package events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import events.EventInterface;

import java.io.Serializable;

public class OneOffEvent implements EventInterface, Serializable {
    private String name;
    private float start;     // hour.minute
    private float end;
    private float duration;     // end - start
    private float date;     // month and day of the year in format mm.dd

    @JsonCreator
    public OneOffEvent(@JsonProperty("name") String name, @JsonProperty("start") float start, @JsonProperty("end") float end, @JsonProperty("date") float date) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.duration = end - start;
        this.date = date;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public float getStartTime() {
        return start;
    }

    @Override
    public void setStartTime(float start) {
        this.start = start;
        this.duration = getEndTime() - start;
    }

    @Override
    public float getEndTime() {
        return end;
    }

    @Override
    public void setEndTime(float end) {
        this.end = end;
        this.duration = end - getStartTime();
    }

    @Override
    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    @Override
    public String getDayOrDate() {
        return Float.toString(date);
    }

    @Override
    public void setDayOrDate(String date) {
        this.date = Float.parseFloat(date);
    }

    @Override
    public int compareTo(EventInterface other) {
        /* Compares 2 events based on their start times
        Returns -1 if current event starts earlier than other event
                1 if current event starts later than other event
                0 if 2 events have the same start time */
        float otherDate = Float.parseFloat(other.getDayOrDate());
        if (Float.compare(this.date, otherDate) != 0){
            return Float.compare(this.date, otherDate);}
        else{
            return Float.compare(this.start, other.getStartTime());}
    }
}