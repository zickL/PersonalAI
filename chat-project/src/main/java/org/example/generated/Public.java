/*
 * This file is generated by jOOQ.
 */
package org.example.generated;


import java.util.Arrays;
import java.util.List;

import org.example.generated.tables.Author;
import org.example.generated.tables.Chathistory;
import org.example.generated.tables.YoutubeSearchCache;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * The schema <code>public</code>.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.author</code>.
     */
    public final Author AUTHOR = Author.AUTHOR;

    /**
     * The table <code>public.chathistory</code>.
     */
    public final Chathistory CHATHISTORY = Chathistory.CHATHISTORY;

    /**
     * The table <code>public.youtube_search_cache</code>.
     */
    public final YoutubeSearchCache YOUTUBE_SEARCH_CACHE = YoutubeSearchCache.YOUTUBE_SEARCH_CACHE;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Author.AUTHOR,
            Chathistory.CHATHISTORY,
            YoutubeSearchCache.YOUTUBE_SEARCH_CACHE
        );
    }
}
