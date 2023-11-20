package service.like.interface_adapter;

import java.util.ArrayList;

public class LikeState {
    private Integer like = 0;

    public LikeState() {}
    public Integer get(){
        return like;
    }
    public void set(Integer like) {
        this.like = like;
    }
}
