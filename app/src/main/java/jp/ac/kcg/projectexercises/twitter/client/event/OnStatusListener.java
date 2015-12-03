package jp.ac.kcg.projectexercises.twitter.client.event;

import twitter4j.Status;

/**
 * OnStatusListener
 */
public interface OnStatusListener {
    /**
     * @param status ステータス
     */
    void onStatus(Status status);
}
