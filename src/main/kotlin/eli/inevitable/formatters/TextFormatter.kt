package eli.inevitable.formatters

import com.google.common.flogger.FluentLogger
import eli.inevitable.LINE_BREAK
import java.io.OutputStream
import java.nio.charset.Charset

/**
 * Generalized interface for constructing structured text.
 */
interface TextFormatter {
    val logger: FluentLogger

    /**
     * @return The total number of lines for the [element][TextFormatter] that includes total padding.
     */
    fun getHeight(): Int

    /**
     * @return The total character width for the [element][TextFormatter] that includes internal padding.
     */
    fun getWidth(): Int

    /**
     * @return A [list of formatted][List] [text][String].
     */
    fun getLines(): List<String>

    /**
     * @param stream An optional [stream][OutputStream] used to print all structured text.
     * Default: [Standard output][System.out]
     * @param charset A [character set][Charset] used to interpret the string as bytes.
     * Default: [Charsets.UTF_8]
     */
    fun println(stream: OutputStream? = null, charset: Charset = Charsets.UTF_8) {
        val text = getLines().joinToString(LINE_BREAK, LINE_BREAK, LINE_BREAK) { it }
        stream?.run { write(text.toByteArray(charset)) } ?: print(text)
    }

    companion object {
        const val LINE_FORMAT = "%s%s%s"
    }
}
