import cv2
import sys
import time

cascPath = sys.argv[1]
faceCascade = cv2.CascadeClassifier(cascPath)

video_capture = cv2.VideoCapture(0)

num_frames = 0
start = time.time()
while True:
    # Capture frame-by-frame
    ret, frame = video_capture.read()
	
    if num_frames < 500:
	frame = cv2.flip(frame,1)
	num_frames = num_frames + 1    
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    faces = faceCascade.detectMultiScale(
        gray,
        scaleFactor=1.1,
        minNeighbors=5,
        minSize=(30, 30),
        flags=cv2.cv.CV_HAAR_SCALE_IMAGE
    )

    # Draw a rectangle around the faces
    for (x, y, w, h) in faces:
        cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 255, 0), 2)

    # Display the resulting frame
    cv2.imshow('Video', frame)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break
total_time = (time.time()-start)
fps = num_frames / total_time
print str(num_frames) + "frames in " + str(total_time) + ' seconds = ' + str(fps)+"fps"
# When everything is done, release the capture
video_capture.release()
cv2.destroyAllWindows()

