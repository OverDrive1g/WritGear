package com.tnninc.writgear.presenter.mappers;

import com.tnninc.writgear.model.database.entities.TagDTO;
import com.tnninc.writgear.presenter.vo.Tag;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class TagListMapper implements Function<List<TagDTO>, List<Tag>> {

    @Inject
    public TagListMapper(){
    }

    @Override
    public List<Tag> apply(List<TagDTO> tagDTOS) throws Exception {
        if (tagDTOS == null) {
            return null;
        }

        List<Tag> tags = Flowable.fromIterable(tagDTOS)
                .map(new Function<TagDTO, Tag>() {
                    @Override
                    public Tag apply(TagDTO tagDTO) throws Exception {
                        return new Tag(tagDTO.getId(), tagDTO.getName());
                    }
                })
                .toList()
                .toFlowable()
                .blockingFirst();
        return tags;
    }
}
