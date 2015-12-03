package jp.ac.kcg.projectexercises.twitter.client.event;

import twitter4j.User;

/**
 * OnUnFollowListener
 */
public interface OnUnFollowListener {
    /**
     * @param source         フォロー解除を実行したユーザー
     * @param unfollowedUser フォロー解除を実行されたユーザー
     */
    void onUnFollow(User source, User unfollowedUser);
}