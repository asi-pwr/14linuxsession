#!flask/bin/python
from flask import Flask, jsonify, abort, make_response, request
from flask_httpauth import HTTPBasicAuth
from flask.json import JSONEncoder
from constants import credentials
from speaker import Speaker
from lecture import Lecture


#API SETUP
class MyJSONEncoder(JSONEncoder):
    def default(self, obj):
        if isinstance(obj, Speaker) or isinstance(obj, Lecture):
            return obj.to_dict()
        return super(MyJSONEncoder, self).default(obj)

app = Flask(__name__)
app.json_encoder = MyJSONEncoder
auth = HTTPBasicAuth()
speakers = Speaker.get_all()
lectures = Lecture.get_all()

@auth.get_password
def get_pw(username):
    if username in credentials:
        return credentials.get(username)
    return None

@app.errorhandler(404)
def not_found(error):
	return make_response(jsonify({'error': 'Not found'}), 404)


#PUBLIC ROUTES
@app.route('/speakers', methods=['GET'])
def get_speakers():
	if 'lecture_id' in request.args:
		_lecture_id = int(request.args['lecture_id'])
		_lecture = [l for l in lectures if l.id==_lecture_id]
		_speaker = [s for s in speakers if s.id==_lecture[0].speaker_id]
		if _speaker and _lecture: 
			return jsonify({
				'speaker': _speaker[0],
				'lecture': _lecture[0]
				})
		else: abort(404)
	return jsonify({'speakers':speakers})

@app.route('/speakers/<int:speaker_id>')
def get_speaker(speaker_id):
	speaker = [s for s in speakers if s.id == speaker_id]
	if not speaker:
		abort(404)
	return jsonify({'speaker': speaker[0]})

@app.route('/lectures', methods=['GET'])
def get_lectures():
	if 'speaker_id' in request.args:
		_speaker_id = int(request.args['speaker_id'])
		_speaker = [s for s in speakers if s.id==_speaker_id]
		_lecture = [l for l in lectures if l.speaker_id==_speaker_id]
		if _speaker and _lecture: 
			return jsonify({
				'speaker': _speaker[0],
				'lecture': _lecture[0]
				})
		else: abort(404)
	return jsonify({'lectures':lectures})

@app.route('/lectures/<int:lecture_id>')
def get_lecture(lecture_id):
	lecture = [l for l in lectures if l.id == lecture_id]
	if not lecture:
		abort(404)
	return jsonify({'lecture': lecture[0]})


#ADMIN PANEL
@app.route('/admin', methods=['GET'])
@auth.login_required
def get_admin_dashboard():
	return jsonify({'credentials':credentials})


if __name__ == "__main__":
	app.run(host="127.0.0.1",port=3007)