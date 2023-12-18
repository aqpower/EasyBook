create definer = easybook@`%` trigger before_delete_post
    before delete
    on post
    for each row
BEGIN
    -- 触发器的具体事件
    DELETE FROM collection WHERE post_id = OLD.id;
    DELETE FROM comment WHERE post_id = OLD.id;
    DELETE FROM easylike WHERE post_id = OLD.id;
    DELETE FROM image WHERE post_id = OLD.id;
    DELETE FROM notify WHERE post_id = OLD.id;
    DELETE FROM violation WHERE post_id = OLD.id;
END;



create definer = easybook@`%` trigger after_insert_like
    after insert
    on easylike
    for each row
begin
    -- 触发器的具体事件
    insert into notify(post_id, user_id, content)
    values (NEW.post_id, New.user_id, '该用户点赞了你的帖子');
    update post
    set like_num = like_num + 1
    where id = NEW.post_id;
end;



create definer = easybook@`%` trigger after_insert_commment
    after insert
    on comment
    for each row
begin
    -- 触发器的具体事件
    insert into notify(post_id, user_id, content)
    values (NEW.post_id, New.user_id, '该用户评论了你的帖子');
end;

create definer = easybook@`%` trigger after_insert_collection
    after insert
    on collection
    for each row
begin
    -- 触发器的具体事件
    insert into notify(post_id, user_id, content)
    values (NEW.post_id, New.user_id, '该用户收藏了你的帖子');
    update post
    set collection_num	= collection_num + 1
    where id = NEW.post_id;
end;

