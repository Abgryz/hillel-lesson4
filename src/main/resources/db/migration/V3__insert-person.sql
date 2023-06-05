insert into person (name, uid)
values
    ('John Smith', '1f0e3dad99908345f7439f8ffabdffc4'),
    ('Emily Johnson', '098f6bcd4621d373cade4e832627b4f6'),
    ('William Brown', '0987f6bcd4621d373cade4e832627b4f6'),
    ('Emma Davis', '875f9e794323b453885f5181f1b624d0b'),
    ('Daniel Wilson', 'd3d9446802a44259755d38e6d163e820'),
    ('Olivia Taylor', '6512bd43d9caa6e02c990b0a82652dca'),
    ('Sophia Miller', '32561bd43d9caa6e02c990b0a82652dca')
on conflict (uid) do nothing;
