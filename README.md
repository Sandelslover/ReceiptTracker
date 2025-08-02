# 📱 Receipt Tracker - Android App

A comprehensive Android application for tracking receipts using OCR technology, with features for expense categorization, monthly summaries, and warranty alerts.

## ✨ Features

### 🔍 OCR Receipt Scanning
- **Camera Integration**: Take photos of receipts and automatically extract data
- **Smart Text Recognition**: Uses Google ML Kit to extract vendor, date, and total amount
- **Intelligent Categorization**: Automatically suggests categories based on vendor keywords
- **Gallery Support**: Select existing images from your photo gallery

### 📊 Receipt Management
- **Multiple Input Methods**: Camera scanning, manual entry, and image selection
- **Comprehensive Search**: Search receipts by vendor name or notes
- **Category Filtering**: Filter receipts by 13 predefined categories
- **Detailed Views**: View complete receipt information with images

### 📈 Analytics & Summaries
- **Monthly Summaries**: Track spending by month with navigation
- **Category Breakdown**: Visual spending analysis by category with percentages
- **Spending Insights**: Total spent, receipt count, and average calculations
- **Progress Visualization**: Color-coded progress bars for category spending

### ⚠️ Warranty Management
- **Warranty Alerts**: Set expiration dates for warranties
- **Smart Notifications**: Get notified 30 days before warranty expiration
- **Background Monitoring**: Automatic daily checks for expiring warranties
- **User Control**: Enable/disable warranty alerts in settings

### 🎨 Modern UI/UX
- **Material Design 3**: Modern, clean interface following Google's design guidelines
- **Dark/Light Theme**: Automatic theme adaptation
- **Intuitive Navigation**: Bottom navigation with three main sections
- **Responsive Design**: Optimized for various screen sizes

## 🏗️ Technical Architecture

### Built With
- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room (SQLite)
- **Camera**: CameraX
- **OCR**: Google ML Kit Text Recognition
- **Image Loading**: Glide
- **Background Work**: WorkManager
- **UI**: Material Design Components

### Project Structure
```
app/
├── src/main/java/com/receipttracker/
│   ├── data/
│   │   ├── database/          # Room database, DAOs, converters
│   │   ├── model/             # Data models and entities
│   │   └── repository/        # Repository pattern implementation
│   ├── ui/
│   │   ├── camera/           # Camera functionality
│   │   ├── receipt/          # Receipt management screens
│   │   ├── receipts/         # Receipt list and filtering
│   │   ├── settings/         # App settings
│   │   └── summary/          # Analytics and summaries
│   └── utils/                # OCR service, warranty alerts
└── src/main/res/
    ├── drawable/             # Vector icons and backgrounds
    ├── layout/               # XML layouts
    ├── menu/                 # Menu definitions
    ├── values/               # Colors, strings, themes
    └── xml/                  # File provider paths
```

## 🚀 Getting Started

### Prerequisites
- **Android Studio**: Arctic Fox (2020.3.1) or newer
- **Android SDK**: API level 24 (Android 7.0) or higher
- **Java**: JDK 8 or higher
- **Gradle**: 8.0 or higher

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd ReceiptTracker
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the ReceiptTracker folder and select it

3. **Sync Project**
   - Android Studio will automatically sync Gradle files
   - Wait for the sync to complete

4. **Build the Project**
   ```bash
   ./gradlew build
   ```

5. **Run the App**
   - Connect an Android device or start an emulator
   - Click the "Run" button in Android Studio
   - Or use the command line:
   ```bash
   ./gradlew installDebug
   ```

### Permissions Required
The app requires the following permissions:
- **Camera**: For taking receipt photos
- **Storage**: For saving and accessing receipt images
- **Notifications**: For warranty expiration alerts

## 📱 Usage Guide

### Adding Receipts

#### Method 1: Camera Scanning
1. Tap the **+** (Add) button on the main screen
2. Select **"Scan with Camera"**
3. Position the receipt in the camera frame
4. Tap the capture button
5. Review and edit the extracted information
6. Save the receipt

#### Method 2: Manual Entry
1. Tap the **+** (Add) button
2. Select **"Manual Entry"**
3. Fill in the receipt details:
   - Vendor name
   - Total amount
   - Date
   - Category
   - Notes (optional)
   - Warranty information (optional)
4. Save the receipt

### Managing Receipts
- **View All**: Browse all receipts in the "Receipts" tab
- **Search**: Use the search bar to find specific receipts
- **Filter**: Filter by category using the filter button
- **Details**: Tap any receipt to view full details
- **Edit/Delete**: Use the menu in receipt details

### Viewing Summaries
1. Navigate to the **"Summary"** tab
2. Use arrow buttons to navigate between months
3. View spending breakdown by category
4. Tap month header to jump to specific month

### Managing Settings
1. Go to the **"Settings"** tab
2. Toggle warranty alerts on/off
3. Export/import data (coming soon)
4. Clear all data if needed

## 🎯 Categories

The app includes 13 predefined spending categories:
- 🍕 Food
- 🎬 Entertainment  
- 📱 Electronics
- 👕 Clothing
- 🚗 Transportation
- 🏥 Healthcare
- ⚡ Utilities
- 🛒 Groceries
- 🎓 Education
- 🏠 Home & Garden
- ✈️ Travel
- 💼 Business
- 📦 Other

## 🔧 Configuration

### OCR Settings
The OCR service automatically:
- Extracts vendor names from the top of receipts
- Identifies monetary amounts and selects the largest (likely total)
- Recognizes various date formats
- Suggests categories based on vendor keywords

### Warranty Alerts
- Alerts are checked daily in the background
- Notifications appear 30 days before expiration
- Can be enabled/disabled per receipt
- Global setting available in preferences

## 🐛 Troubleshooting

### Common Issues

**OCR not working properly:**
- Ensure good lighting when taking photos
- Keep the receipt flat and in focus
- Try different angles if text isn't recognized

**Camera not opening:**
- Check camera permissions in device settings
- Restart the app
- Ensure device has a working camera

**Notifications not appearing:**
- Enable notification permissions
- Check that warranty alerts are enabled in settings
- Verify warranty expiry dates are set correctly

**App crashes:**
- Clear app data and cache
- Ensure device meets minimum requirements
- Check for available storage space

## 🔄 Future Enhancements

Planned features for future releases:
- [ ] Data export/import functionality
- [ ] Receipt editing capabilities
- [ ] Cloud backup integration
- [ ] Receipt sharing
- [ ] Advanced analytics and charts
- [ ] Custom categories
- [ ] Multi-currency support
- [ ] Receipt templates

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Google ML Kit for OCR capabilities
- Material Design team for UI components
- CameraX team for modern camera API
- Room database for local storage
- All open-source contributors

## 📞 Support

If you encounter any issues or have questions:
1. Check the troubleshooting section above
2. Search existing issues in the repository
3. Create a new issue with detailed information
4. Include device information and steps to reproduce

---

**Built with ❤️ for efficient receipt management**
