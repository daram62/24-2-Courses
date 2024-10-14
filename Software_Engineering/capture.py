import cv2
import numpy as np

# 동영상 파일 경로
video_path = '2.mp4'

# 동영상 캡처 객체 생성
cap = cv2.VideoCapture(video_path)

# 비디오가 열렸는지 확인
if not cap.isOpened():
    print(f"Error: Could not open video file {video_path}")
    exit()

# 동영상의 총 프레임 수와 FPS 가져오기
total_frames = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
fps = cap.get(cv2.CAP_PROP_FPS)

# FPS 값이 0인지 확인
if fps == 0:
    print("Error: FPS value is 0. Check the video file.")
    exit()

video_duration = total_frames / fps  # 총 재생 시간(초)

# 이전 프레임 초기화
prev_frame = None
slide_count = 42

# 프레임 간 차이를 계산하기 위한 임계값 설정
threshold = 3
# 프레임 카운터 초기화
frame_counter = 0

# 영상 재생을 위한 창 생성 및 크기 조절
cv2.namedWindow('Video', cv2.WINDOW_NORMAL)
cv2.resizeWindow('Video', 200, 150)

while cap.isOpened():
    ret, frame = cap.read()
    if not ret:
        break

    frame_counter += 1
    current_time = frame_counter / fps

    # 매 20번째 프레임만 처리
    if frame_counter % 20 == 0:
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        gray = cv2.resize(gray, (640, 480))
        gray = cv2.GaussianBlur(gray, (5, 5), 0)

        if prev_frame is None:
            slide_count += 1
            cv2.imwrite(f'slide_{slide_count}.png', frame)
            print(f'Slide {slide_count} (첫 번째 프레임) captured.')
            prev_frame = gray
        else:
            frame_diff = cv2.absdiff(prev_frame, gray)
            diff_mean = np.mean(frame_diff)
            print(f'Frame {frame_counter}, diff_mean: {diff_mean}')

            if diff_mean > threshold:
                slide_count += 1
                cv2.imwrite(f'slide_{slide_count}.png', frame)
                print(f'Slide {slide_count} captured.')

            prev_frame = gray

    cv2.imshow('Video', frame)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()