package com.tnninc.writgear;


import com.tnninc.writgear.model.database.entities.NoteDTO;
import com.tnninc.writgear.model.database.entities.TagDTO;
import com.tnninc.writgear.presenter.vo.Tag;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.tnninc.writgear.utils.Converter.getTagsFromTagDTOs;
import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void addition_isCorrect() throws Exception {
//        List<TagDTO> tags = new ArrayList<>();
//        tags.add(new TagDTO(1L, "name1"));
//        tags.add(new TagDTO(2L, "name2"));
//
//        List<Tag> resultTags = getTagsFromTagDTOs(tags);

        assertEquals(4, 2 + 2);
    }
}
