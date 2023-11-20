package service.like.use_case;

public class LikeOutputData {
    private final Integer like;

    public LikeOutputData(Integer like) {
        this.like = like;
    }

    public Integer get(){
        return like;
    }
}
