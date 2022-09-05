package karpiuk.ivan.realm.model

import io.realm.kotlin.ext.realmListOf
import karpiuk.ivan.repository.model.local.*
import org.junit.Assert
import org.junit.Test

internal class ConvertersKtTest {

    @Test
    fun local_feed_can_be_mapped_to_realm_feed() {
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

        val realmFeed = localFeed.asRealmObject()
        Assert.assertEquals(localFeed.author?.name, realmFeed.author?.name)
        Assert.assertEquals(localFeed.author?.url, realmFeed.author?.url)
        Assert.assertEquals(localFeed.copyright, realmFeed.copyright)
        Assert.assertEquals(localFeed.country, realmFeed.country)
        Assert.assertEquals(localFeed.icon, realmFeed.icon)
        Assert.assertEquals(localFeed.id, realmFeed.id)
        Assert.assertEquals(localFeed.links.first().self, realmFeed.links.first().self)
        Assert.assertEquals(localFeed.results.first().artistId, realmFeed.results.first().artistId)
        Assert.assertEquals(localFeed.results.first().artistName, realmFeed.results.first().artistName)
        Assert.assertEquals(localFeed.results.first().artistUrl, realmFeed.results.first().artistUrl)
        Assert.assertEquals(localFeed.results.first().artworkUrl100, realmFeed.results.first().artworkUrl100)
        Assert.assertEquals(localFeed.results.first().id, realmFeed.results.first().id)
        Assert.assertEquals(localFeed.results.first().kind, realmFeed.results.first().kind)
        Assert.assertEquals(localFeed.results.first().name, realmFeed.results.first().name)
        Assert.assertEquals(localFeed.results.first().releaseDate, realmFeed.results.first().releaseDate)
        Assert.assertEquals(localFeed.results.first().url, realmFeed.results.first().url)
        Assert.assertEquals(
            localFeed.results.first().genres.first().genreId,
            realmFeed.results.first().genres.first().genreId
        )
        Assert.assertEquals(
            localFeed.results.first().genres.first().url,
            realmFeed.results.first().genres.first().url
        )
        Assert.assertEquals(
            localFeed.results.first().genres.first().name,
            realmFeed.results.first().genres.first().name
        )
    }

    @Test
    fun realm_feed_can_be_mapped_to_local_feed() {
        val realmFeed = RealmFeed(
            author = RealmAuthor(name = "asda", url = "qweq"),
            copyright = "asd",
            country = "gsda",
            icon = "sdfa",
            id = "asdgad",
            links = realmListOf(RealmLink(self = "asdfg")),
            results = realmListOf(
                RealmResult(
                    artistId = "asdf",
                    artistName = "dsg",
                    artistUrl = "gsdfg",
                    artworkUrl100 = "sddfg",
                    contentAdvisoryRating = "sdffg",
                    genres = realmListOf(
                        RealmGenre(genreId = "sdsdffg", name = "sdhfdgfg", url = "sdfghg")
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

        val localModel = realmFeed.asExternalModel()
        Assert.assertEquals(realmFeed.author?.name, localModel.author?.name)
        Assert.assertEquals(realmFeed.author?.url, localModel.author?.url)
        Assert.assertEquals(realmFeed.copyright, localModel.copyright)
        Assert.assertEquals(realmFeed.country, localModel.country)
        Assert.assertEquals(realmFeed.icon, localModel.icon)
        Assert.assertEquals(realmFeed.id, localModel.id)
        Assert.assertEquals(realmFeed.links.first().self, localModel.links.first().self)
        Assert.assertEquals(realmFeed.results.first().artistId, localModel.results.first().artistId)
        Assert.assertEquals(realmFeed.results.first().artistName, localModel.results.first().artistName)
        Assert.assertEquals(realmFeed.results.first().artistUrl, localModel.results.first().artistUrl)
        Assert.assertEquals(realmFeed.results.first().artworkUrl100, localModel.results.first().artworkUrl100)
        Assert.assertEquals(realmFeed.results.first().id, localModel.results.first().id)
        Assert.assertEquals(realmFeed.results.first().kind, localModel.results.first().kind)
        Assert.assertEquals(realmFeed.results.first().name, localModel.results.first().name)
        Assert.assertEquals(realmFeed.results.first().releaseDate, localModel.results.first().releaseDate)
        Assert.assertEquals(realmFeed.results.first().url, localModel.results.first().url)
        Assert.assertEquals(
            realmFeed.results.first().genres.first().genreId,
            localModel.results.first().genres.first().genreId
        )
        Assert.assertEquals(
            realmFeed.results.first().genres.first().url,
            localModel.results.first().genres.first().url
        )
        Assert.assertEquals(
            realmFeed.results.first().genres.first().name,
            localModel.results.first().genres.first().name
        )
    }
}