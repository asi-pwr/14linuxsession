class Lecture(object):

	def __init__(self, _id, speaker_id, title, description, day, start_time, end_time):
		self.id = _id
		self.speaker_id = speaker_id
		self.title = title
		self.description = description
		self.day = day
		self.start_time = start_time
		self.end_time = end_time

	def __str__(self):
		return 'Lecture #{}: {}'.format(self.id, self.title)
		
	def to_dict(self,):
		return {'id': self.id, 'title': self.title, 'description': self.description, 'speaker_id': self.speaker_id, 
				'day': self.day, 'start_time': self.start_time, 'end_time': self.end_time}

	@staticmethod
	def get_all():
		return _lectures

_lectures = [
	Lecture(
		1,
		1,
		u'Emigrating to UK 101',
		u'How to escape your country and make your government pay for it',
		1,
		'12:00',
		'13:00'
		),
	Lecture(
		2,
		3,
		u'Why nvidia sux',
		u'Totally objective outlook on suction capabilities of certain big it companies',
		1,
		'14:30',
		'15:30'
		),
	Lecture(
		3,
		2,
		u'Lorem ipsum',
		u'Lorem ipsum dolor i don\'t speak no latin',
		2,
		'12:00',
		'13:00'
		)
]