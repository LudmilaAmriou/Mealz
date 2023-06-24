import android.app.NotificationManager
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.core.app.NotificationCompat
import com.example.mealz.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Check if the message contains a notification payload
        remoteMessage.notification?.let { notification ->
            val title = notification.title // Notification title
            val body = notification.body // Notification body

            // Create the notification layout view
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val notificationView = inflater.inflate(R.layout.notification, null)
            val titleTextView = notificationView.findViewById<TextView>(R.id.title)
            val messageTextView = notificationView.findViewById<TextView>(R.id.message)

            // Set the notification content
            titleTextView.text = title
            messageTextView.text = body

            // Create the notification builder
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.mealz_logo)
                .setContentTitle(title)
                .setContentText(body)
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                .setAutoCancel(true)

            // Show the notification
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(NOTIFICATION_ID, builder.build())
        }
    }

    override fun onNewToken(token: String) {
        // Handle token refresh if needed
        // You can send the token to your server to associate it with the user
    }

    companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val NOTIFICATION_ID = 1
    }
}