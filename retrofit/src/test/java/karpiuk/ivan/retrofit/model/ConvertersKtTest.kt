package karpiuk.ivan.retrofit.model

import org.junit.Assert
import org.junit.Test

internal class ConvertersKtTest {

    @Test
    fun rest_feed_can_be_mapped_to_network_entity() {
        val restFeed = RestFeed(
            author = RestAuthor(name = "asda", url = "qweq"),
            copyright = "asd",
            country = "gsda",
            icon = "sdfa",
            id = "asdgad",
            links = listOf(RestLink(self = "asdfg")),
            results = listOf(
                RestResult(
                    artistId = "asdf",
                    artistName = "dsg",
                    artistUrl = "gsdfg",
                    artworkUrl100 = "sddfg",
                    contentAdvisoryRating = "sdffg",
                    genres = listOf(
                        RestGenre(genreId = "sdsdffg", name = "sdhfdgfg", url = "sdfghg")
                    ),
                    id = "sdfg",
                    kind = "jdhdsfghf",
                    name = "asdfgts",
                    releaseDate = "hdgfad",
                    url = "dfssadf"
                )
            ),
            title = "sdfhfgs",
            updated = "dsfhfwa"
        )

        val networkFeed = restFeed.asExternalModel()
        Assert.assertEquals(restFeed.author.name, networkFeed.author.name)
        Assert.assertEquals(restFeed.author.url, networkFeed.author.url)
        Assert.assertEquals(restFeed.copyright, networkFeed.copyright)
        Assert.assertEquals(restFeed.country, networkFeed.country)
        Assert.assertEquals(restFeed.icon, networkFeed.icon)
        Assert.assertEquals(restFeed.id, networkFeed.id)
        Assert.assertEquals(restFeed.links.first().self, networkFeed.links.first().self)
        Assert.assertEquals(restFeed.results.first().artistId, networkFeed.results.first().artistId)
        Assert.assertEquals(restFeed.results.first().artistName, networkFeed.results.first().artistName)
        Assert.assertEquals(restFeed.results.first().artistUrl, networkFeed.results.first().artistUrl)
        Assert.assertEquals(restFeed.results.first().artworkUrl100, networkFeed.results.first().artworkUrl100)
        Assert.assertEquals(restFeed.results.first().id, networkFeed.results.first().id)
        Assert.assertEquals(restFeed.results.first().kind, networkFeed.results.first().kind)
        Assert.assertEquals(restFeed.results.first().name, networkFeed.results.first().name)
        Assert.assertEquals(restFeed.results.first().releaseDate, networkFeed.results.first().releaseDate)
        Assert.assertEquals(restFeed.results.first().url, networkFeed.results.first().url)
        Assert.assertEquals(
            restFeed.results.first().genres.first().genreId,
            networkFeed.results.first().genres.first().genreId
        )
        Assert.assertEquals(restFeed.results.first().genres.first().url, networkFeed.results.first().genres.first().url)
        Assert.assertEquals(
            restFeed.results.first().genres.first().name,
            networkFeed.results.first().genres.first().name
        )
    }
}