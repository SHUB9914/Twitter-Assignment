
import java.text.SimpleDateFormat
import twitter4j.conf.ConfigurationBuilder
import twitter4j.{Query, Twitter, TwitterFactory}
import scala.collection.JavaConverters._
import scala.io.Source


class Snippet {

  def getTweets(search:String): (List[MyTweets],List[twitter4j.Status]) = {
    val result=for{line<-Source.fromFile("G:\\document\\properties.txt").getLines() // retieve keys from file
              ln=line}yield ln
    val lines=result.toList

    val consumerKey = lines(0)
    val consumerSecretKey = lines(1)
    val accessToken = lines(2)
    val accessTokenSecret = lines(3)

    val configurationBuilder = new ConfigurationBuilder()
    configurationBuilder.setDebugEnabled(false)
      .setOAuthConsumerKey(consumerKey)
      .setOAuthConsumerSecret(consumerSecretKey)
      .setOAuthAccessToken(accessToken)
      .setOAuthAccessTokenSecret(accessTokenSecret)

    val twitter: Twitter = new TwitterFactory(configurationBuilder.build()).getInstance()

    val query = new Query(search)
    val number=100
    query.setCount(number)
    val list = twitter.search(query)
    val tweets = list.getTweets.asScala.toList
    val dt1=new SimpleDateFormat("dd-MM-yyyy")
    val allTweets = tweets.map { tweet =>
     MyTweets(tweet.getText, tweet.getUser.getScreenName, dt1.format(tweet.getCreatedAt))
    }
    (allTweets,tweets)

  }


}