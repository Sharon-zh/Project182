package service.comment;
import data_access.FileRecipeDataAccessObject;
import data_access.InmemoryRecipeDataAccessObject;
import org.junit.jupiter.api.Test;
import service.comment.use_case.CommentInputData;
import service.comment.use_case.*;
import service.comment.interface_adapter.*;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class CommentInteractorTest {
    @Test
    void successTest(){
        CommentInputData commentInputData = new CommentInputData("Beef Asado", "kevin", "good");
        CommentDataAccessInterface commentRepository = new InmemoryRecipeDataAccessObject();
        CommentOutputBoundary seccussPresenter = new CommentOutputBoundary() {
            @Override
            public void prepareSuccessView(CommentOutputData recipe_comment) {
                assertEquals("Beef Asado", recipe_comment.getRecipeName());
                assertEquals("kevin: good", recipe_comment.getComment());
            }
        };

        CommentInputBoundary commentInputBoundary = new CommentInteractor(commentRepository, seccussPresenter);
        commentInputBoundary.excute(commentInputData);

    }
}
