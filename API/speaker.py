class Speaker(object):
	
	def __init__(self, _id, name, description, img_url):
		self.id = _id
		self.name = name
		self.description = description
		self.img_url = img_url

	def __str__(self):
		return 'Speaker #{}: {}'.format(self.id, self.name)
		
	def to_dict(self,):
		return {'id': self.id, 'name': self.name, 'description': self.description, 'img_url': self.img_url}

	@staticmethod
	def get_all():
		return _speakers

_speakers = [
	Speaker(
		1,
		u'Wojciech Czerpak',
		u'Quite a great chap frankly',
		'http://68.media.tumblr.com/b08214bffa07f0a79938697589ed4fe2/tumblr_inline_odd2x9PvYg1raprkq_500.gif'
		),
	Speaker(
		3,
		u'Linus Torvalds',
		u'Hates nvidia quite a bit',
		'http://68.media.tumblr.com/9ba8fe087f2bc0eda23f424bffb20fb0/tumblr_inline_odd3dlLIY31raprkq_500.gif'
		),
	Speaker(
		2,
		u'Jan Kowalski',
		u'A basic white dude',
		'http://68.media.tumblr.com/8f0c4363da17d0e987af1bc02f9cc10b/tumblr_inline_odd3llOGu51raprkq_500.gif'
		)
]
