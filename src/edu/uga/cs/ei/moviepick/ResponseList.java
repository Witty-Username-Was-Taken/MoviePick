package edu.uga.cs.ei.moviepick;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "responseList")
public class ResponseList {

    private List<Object> list;

    public List<Object> getList() {
        return list;
    }

    public void setList( List<Object> list ) {
        this.list = list;
    }
}
