from db import db

class Speaker(db.Model):
	id = db.Column(db.Integer, primary_key=True)
	name = db.Column(db.String(25))
	description = db.Column(db.String(500))
	img_url = db.Column(db.String(60))

	
	def __init__(self, id_, name, description, img_url):
		self.id = id_
		self.name = name
		self.description = description
		self.img_url = img_url

	def __str__(self):
		return 'Speaker #{}: {}'.format(self.id, self.name)
		
	def to_dict(self):
		return {'id': self.id, 'name': self.name, 'description': self.description, 'img_url': self.img_url}

	@staticmethod
	def get(id_ = None):
		print id_
		return _speakers if id_ is None else Speaker.query.filter_by(id=id_).first()

_speakers = [
	Speaker(
		1,
		'Wojciech Czerpak',
		'Quite a great chap frankly',
		'http://68.media.tumblr.com/b08214bffa07f0a79938697589ed4fe2/tumblr_inline_odd2x9PvYg1raprkq_500.gif'
		),
	Speaker(
		3,
		'Linus Torvalds',
		'Hates nvidia quite a bit',
		'http://68.media.tumblr.com/9ba8fe087f2bc0eda23f424bffb20fb0/tumblr_inline_odd3dlLIY31raprkq_500.gif'
		),
	Speaker(
		2,
		'Jan Kowalski',
		'A basic white dude',
		'http://68.media.tumblr.com/8f0c4363da17d0e987af1bc02f9cc10b/tumblr_inline_odd3llOGu51raprkq_500.gif'
		)
]



