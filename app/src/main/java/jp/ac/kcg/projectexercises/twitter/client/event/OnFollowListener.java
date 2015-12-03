package jp.ac.kcg.projectexercises.twitter.client.event;

import twitter4j.User;

/**
 * OnFollowListener
 */
public interface OnFollowListener {
    /**
     * @param source       フォローを実行したユーザー
     * @param followedUser フォローされたユーザー
     */
    void onFollow(User source, User followedUser);
}
