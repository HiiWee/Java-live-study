package dispatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface Post {
    void postOn(SNS sns);
}

interface SNS {
    void post(Post post);

}

class Picture implements Post {

    @Override
    public void postOn(SNS sns) {
        sns.post(this);
    }
}

class Text implements Post {

    @Override
    public void postOn(SNS sns) {
        sns.post(this);
    }
}

class Video implements Post {

    @Override
    public void postOn(SNS sns) {
        sns.post(this);
    }
}

class Facebook implements SNS {
    @Override
    public void post(Post post) {
        System.out.println(post.getClass() + " by facebook");
    }

}

class Twitter implements SNS {

    @Override
    public void post(Post post) {
        System.out.println(post.getClass() + " by twitter");
    }
}

class Instagram implements SNS {
    @Override
    public void post(Post post) {
        System.out.println(post.getClass() + " by instagram");
    }
}

public class DoubleDispatch {
    public static void main(String[] args) {
        List<Post> posts = Arrays.asList(new Text(), new Picture());
        List<SNS> sns = Arrays.asList(new Facebook(), new Twitter());

        posts.forEach(post -> sns.forEach(s -> post.postOn(s)));
    }
}
