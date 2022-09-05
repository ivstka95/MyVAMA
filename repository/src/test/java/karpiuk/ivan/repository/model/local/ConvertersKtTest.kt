package karpiuk.ivan.repository.model.local

import karpiuk.ivan.model.Author
import karpiuk.ivan.model.Feed
import karpiuk.ivan.model.Genre
import karpiuk.ivan.model.Link
import karpiuk.ivan.model.Result
import org.junit.Assert
import org.junit.Test


internal class ConvertersKtTest {
    @Test
    fun local_feed_can_be_mapped_to_domain_entity() {
        val localFeed = LocalFeed(
            author = LocalAuthor(name = "asda", url = "qweq"),
            copyright = "asd",
            country = "gsda",
            icon = "sdfa",
            id = "asdgad",
            links = listOf(LocalLink(self = "asdfg")),
            results = listOf(
                LocalResult(
                    artistId = "asdf",
                    artistName = "dsg",
                    artistUrl = "gsdfg",
                    artworkUrl100 = "sddfg",
                    contentAdvisoryRating = "sdffg",
                    genres = listOf(
                        LocalGenre(genreId = "sdsdffg", name = "sdhfdgfg", url = "sdfghg")
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

        val domainFeed = localFeed.asDomainModel()
        Assert.assertEquals(localFeed.author?.name, domainFeed.author?.name)
        Assert.assertEquals(localFeed.author?.url, domainFeed.author?.url)
        Assert.assertEquals(localFeed.copyright, domainFeed.copyright)
        Assert.assertEquals(localFeed.country, domainFeed.country)
        Assert.assertEquals(localFeed.icon, domainFeed.icon)
        Assert.assertEquals(localFeed.id, domainFeed.id)
        Assert.assertEquals(localFeed.links.first().self, domainFeed.links.first().self)
        Assert.assertEquals(localFeed.results.first().artistId, domainFeed.results.first().artistId)
        Assert.assertEquals(localFeed.results.first().artistName, domainFeed.results.first().artistName)
        Assert.assertEquals(localFeed.results.first().artistUrl, domainFeed.results.first().artistUrl)
        Assert.assertEquals(localFeed.results.first().artworkUrl100, domainFeed.results.first().artworkUrl100)
        Assert.assertEquals(localFeed.results.first().id, domainFeed.results.first().id)
        Assert.assertEquals(localFeed.results.first().kind, domainFeed.results.first().kind)
        Assert.assertEquals(localFeed.results.first().name, domainFeed.results.first().name)
        Assert.assertEquals(localFeed.results.first().releaseDate, domainFeed.results.first().releaseDate)
        Assert.assertEquals(localFeed.results.first().url, domainFeed.results.first().url)
        Assert.assertEquals(
            localFeed.results.first().genres.first().genreId,
            domainFeed.results.first().genres.first().genreId
        )
        Assert.assertEquals(
            localFeed.results.first().genres.first().url,
            domainFeed.results.first().genres.first().url
        )
        Assert.assertEquals(
            localFeed.results.first().genres.first().name,
            domainFeed.results.first().genres.first().name
        )
    }

    @Test
    fun domain_feed_can_be_mapped_to_local_entity() {
        val domainFeed = Feed(
            author = Author(name = "asda", url = "qweq"),
            copyright = "asd",
            country = "gsda",
            icon = "sdfa",
            id = "asdgad",
            links = listOf(Link(self = "asdfg")),
            results = listOf(
                Result(
                    artistId = "asdf",
                    artistName = "dsg",
                    artistUrl = "gsdfg",
                    artworkUrl100 = "sddfg",
                    contentAdvisoryRating = "sdffg",
                    genres = listOf(
                        Genre(genreId = "sdsdffg", name = "sdhfdgfg", url = "sdfghg")
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

        val localModel = domainFeed.asLocalModel()
        Assert.assertEquals(domainFeed.author?.name, localModel.author?.name)
        Assert.assertEquals(domainFeed.author?.url, localModel.author?.url)
        Assert.assertEquals(domainFeed.copyright, localModel.copyright)
        Assert.assertEquals(domainFeed.country, localModel.country)
        Assert.assertEquals(domainFeed.icon, localModel.icon)
        Assert.assertEquals(domainFeed.id, localModel.id)
        Assert.assertEquals(domainFeed.links.first().self, localModel.links.first().self)
        Assert.assertEquals(domainFeed.results.first().artistId, localModel.results.first().artistId)
        Assert.assertEquals(domainFeed.results.first().artistName, localModel.results.first().artistName)
        Assert.assertEquals(domainFeed.results.first().artistUrl, localModel.results.first().artistUrl)
        Assert.assertEquals(domainFeed.results.first().artworkUrl100, localModel.results.first().artworkUrl100)
        Assert.assertEquals(domainFeed.results.first().id, localModel.results.first().id)
        Assert.assertEquals(domainFeed.results.first().kind, localModel.results.first().kind)
        Assert.assertEquals(domainFeed.results.first().name, localModel.results.first().name)
        Assert.assertEquals(domainFeed.results.first().releaseDate, localModel.results.first().releaseDate)
        Assert.assertEquals(domainFeed.results.first().url, localModel.results.first().url)
        Assert.assertEquals(
            domainFeed.results.first().genres.first().genreId,
            localModel.results.first().genres.first().genreId
        )
        Assert.assertEquals(
            domainFeed.results.first().genres.first().url,
            localModel.results.first().genres.first().url
        )
        Assert.assertEquals(
            domainFeed.results.first().genres.first().name,
            localModel.results.first().genres.first().name
        )
    }
}