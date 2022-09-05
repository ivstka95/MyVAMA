package karpiuk.ivan.repository.model.network

import org.junit.Assert
import org.junit.Test

internal class ConvertersKtTest {

    @Test
    fun network_feed_can_be_mapped_to_model_entity() {
        val networkFeed = NetworkFeed(
            author = NetworkAuthor(name = "asda", url = "qweq"),
            copyright = "asd",
            country = "gsda",
            icon = "sdfa",
            id = "asdgad",
            links = listOf(NetworkLink(self = "asdfg")),
            results = listOf(
                NetworkResult(
                    artistId = "asdf",
                    artistName = "dsg",
                    artistUrl = "gsdfg",
                    artworkUrl100 = "sddfg",
                    contentAdvisoryRating = "sdffg",
                    genres = listOf(
                        NetworkGenre(genreId = "sdsdffg", name = "sdhfdgfg", url = "sdfghg")
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

        val domainFeed = networkFeed.asDomainModel()
        Assert.assertEquals(networkFeed.author.name, domainFeed.author?.name)
        Assert.assertEquals(networkFeed.author.url, domainFeed.author?.url)
        Assert.assertEquals(networkFeed.copyright, domainFeed.copyright)
        Assert.assertEquals(networkFeed.country, domainFeed.country)
        Assert.assertEquals(networkFeed.icon, domainFeed.icon)
        Assert.assertEquals(networkFeed.id, domainFeed.id)
        Assert.assertEquals(networkFeed.links.first().self, domainFeed.links.first().self)
        Assert.assertEquals(networkFeed.results.first().artistId, domainFeed.results.first().artistId)
        Assert.assertEquals(networkFeed.results.first().artistName, domainFeed.results.first().artistName)
        Assert.assertEquals(networkFeed.results.first().artistUrl, domainFeed.results.first().artistUrl)
        Assert.assertEquals(networkFeed.results.first().artworkUrl100, domainFeed.results.first().artworkUrl100)
        Assert.assertEquals(networkFeed.results.first().id, domainFeed.results.first().id)
        Assert.assertEquals(networkFeed.results.first().kind, domainFeed.results.first().kind)
        Assert.assertEquals(networkFeed.results.first().name, domainFeed.results.first().name)
        Assert.assertEquals(networkFeed.results.first().releaseDate, domainFeed.results.first().releaseDate)
        Assert.assertEquals(networkFeed.results.first().url, domainFeed.results.first().url)
        Assert.assertEquals(
            networkFeed.results.first().genres.first().genreId,
            domainFeed.results.first().genres.first().genreId
        )
        Assert.assertEquals(
            networkFeed.results.first().genres.first().url,
            domainFeed.results.first().genres.first().url
        )
        Assert.assertEquals(
            networkFeed.results.first().genres.first().name,
            domainFeed.results.first().genres.first().name
        )
    }

    @Test
    fun network_feed_can_be_mapped_to_local_feed() {
        val networkFeed = NetworkFeed(
            author = NetworkAuthor(name = "asda", url = "qweq"),
            copyright = "asd",
            country = "gsda",
            icon = "sdfa",
            id = "asdgad",
            links = listOf(NetworkLink(self = "asdfg")),
            results = listOf(
                NetworkResult(
                    artistId = "asdf",
                    artistName = "dsg",
                    artistUrl = "gsdfg",
                    artworkUrl100 = "sddfg",
                    contentAdvisoryRating = "sdffg",
                    genres = listOf(
                        NetworkGenre(genreId = "sdsdffg", name = "sdhfdgfg", url = "sdfghg")
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

        val domainFeed = networkFeed.asLocalModel()
        Assert.assertEquals(networkFeed.author.name, domainFeed.author?.name)
        Assert.assertEquals(networkFeed.author.url, domainFeed.author?.url)
        Assert.assertEquals(networkFeed.copyright, domainFeed.copyright)
        Assert.assertEquals(networkFeed.country, domainFeed.country)
        Assert.assertEquals(networkFeed.icon, domainFeed.icon)
        Assert.assertEquals(networkFeed.id, domainFeed.id)
        Assert.assertEquals(networkFeed.links.first().self, domainFeed.links.first().self)
        Assert.assertEquals(networkFeed.results.first().artistId, domainFeed.results.first().artistId)
        Assert.assertEquals(networkFeed.results.first().artistName, domainFeed.results.first().artistName)
        Assert.assertEquals(networkFeed.results.first().artistUrl, domainFeed.results.first().artistUrl)
        Assert.assertEquals(networkFeed.results.first().artworkUrl100, domainFeed.results.first().artworkUrl100)
        Assert.assertEquals(networkFeed.results.first().id, domainFeed.results.first().id)
        Assert.assertEquals(networkFeed.results.first().kind, domainFeed.results.first().kind)
        Assert.assertEquals(networkFeed.results.first().name, domainFeed.results.first().name)
        Assert.assertEquals(networkFeed.results.first().releaseDate, domainFeed.results.first().releaseDate)
        Assert.assertEquals(networkFeed.results.first().url, domainFeed.results.first().url)
        Assert.assertEquals(
            networkFeed.results.first().genres.first().genreId,
            domainFeed.results.first().genres.first().genreId
        )
        Assert.assertEquals(
            networkFeed.results.first().genres.first().url,
            domainFeed.results.first().genres.first().url
        )
        Assert.assertEquals(
            networkFeed.results.first().genres.first().name,
            domainFeed.results.first().genres.first().name
        )
    }
}