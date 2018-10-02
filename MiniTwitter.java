/**
 * Definition of Tweet:
 * public class Tweet {
 *     public int id;
 *     public int user_id;
 *     public String text;
 *     public static Tweet create(int user_id, String tweet_text) {
 *         // This will create a new tweet object,
 *         // and auto fill id
 *     }
 * }
 */


public class MiniTwitter {
    LinkedList<Tweet> ls; // list of tweets
    Map<Integer, Set<Integer>> map; // friendship table
    
    public MiniTwitter() {
        ls = new LinkedList<>();
        map = new HashMap<>();
    }

    /*
     * @param user_id: An integer
     * @param tweet_text: a string
     * @return: a tweet
     */
    public Tweet postTweet(int user_id, String tweet_text) {
        Tweet post = Tweet.create(user_id, tweet_text);
        ls.addFirst(post);
        return post;
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new feeds recently and sort by timeline
     */
    public List<Tweet> getNewsFeed(int user_id) {
        List<Tweet> res = new ArrayList<>();
        
        // user_id has not following, then just get her tweets
        if (map.get(user_id) == null) {
            return getTimeline(user_id);
        }
        
        // otherwise, get tweets from her and her following
        for (Tweet t : ls) {
            if (t.user_id == user_id ||
                map.get(user_id).contains(t.user_id)) {
                res.add(t);
            }
            
            if (res.size() == 10) {
                return res;
            }
        }
        
        return res;
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new posts recently and sort by timeline
     */
    public List<Tweet> getTimeline(int user_id) {
        List<Tweet> res = new ArrayList<>();
        
        for (Tweet t : ls) {
            if (t.user_id == user_id) {
                res.add(t);
            }
            
            if (res.size() == 10) {
                return res;
            }
        }
        
        return res;
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int from_user_id, int to_user_id) {
        map.putIfAbsent(from_user_id, new HashSet<>());
        map.get(from_user_id).add(to_user_id);
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int from_user_id, int to_user_id) {
        if (map.containsKey(from_user_id) &&
            map.get(from_user_id).contains(to_user_id)) {
            map.get(from_user_id).remove(to_user_id);
        }
    }
}